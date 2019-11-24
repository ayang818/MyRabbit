package com.ayang818.myrabbit.utils;


import com.ayang818.myrabbit.annotation.Select;
import com.ayang818.myrabbit.conf.Configuration;
import com.ayang818.myrabbit.conf.Mapper;
import com.ayang818.myrabbit.io.Resources;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  用于解析配置文件
 */
public class XMLConfigParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(XMLConfigParser.class);
    /**
     * 解析主配置文件，把里面的内容填充到DefaultSqlSession所需要的地方
     */
    public static Configuration loadConfiguration(InputStream config){
        try{
            Configuration configuration = new Configuration();
            SAXReader reader = new SAXReader();
            Document document = reader.read(config);
            Element root = document.getRootElement();
            List<Node> propertyElements = root.selectNodes("//property");
            for(Node propertyElement : propertyElements){
                Element element = (Element) propertyElement;
                String name = element.attributeValue("name");
                if("driver".equals(name)){
                    String driver = element.attributeValue("value");
                    configuration.setDriver(driver);
                }
                if("url".equals(name)){
                    String url = element.attributeValue("value");
                    configuration.setUrl(url);
                }
                if("username".equals(name)){
                    String username = element.attributeValue("value");
                    configuration.setUsername(username);
                }
                if("password".equals(name)){
                    String password = element.attributeValue("value");
                    configuration.setPassword(password);
                }
            }
            // 取出mappers中的所有mapper标签，
            List<Node> mapperElements = root.selectNodes("//mappers/mapper");
            Map<String, Mapper> mappers = new HashMap<>();
            for(Node mapperElement : mapperElements){
                Element element = (Element) mapperElement;
                Attribute attribute = element.attribute("resource");
                if(attribute != null){
                    LOGGER.info("using xml");
                    String mapperPath = attribute.getValue();
                    mappers.putAll(loadMapperConfiguration(mapperPath));
                    configuration.setMap(mappers);
                } else{
                    LOGGER.info("using annotation");
                    String mapperClassPath = element.attributeValue("class");
                    mappers.putAll(loadMapperAnnotation(mapperClassPath));
                    configuration.setMap(mappers);
                }
            }
            return configuration;
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            try {
                config.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    /**
     * 根据传入的参数，解析XML，并且封装到Map中
     * @param mapperPath    映射配置文件的位置
     * @return  map中包含了获取的唯一标识（key是由mapper的全限定类名和方法名组成）
     *          以及执行所需的必要信息（value是一个Mapper对象，里面存放的是执行的SQL语句和要封装的实体类全限定类名）
     */
    private static Map<String,Mapper> loadMapperConfiguration(String mapperPath)throws IOException {
        InputStream in = null;
        try{
            Map<String,Mapper> mappers = new HashMap<>();
            in = Resources.getResourceAsStream(mapperPath);
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            Element root = document.getRootElement();
            String namespace = root.attributeValue("namespace");
            List<Node> selectElements = root.selectNodes("//select");
            for (Node selectElement : selectElements) {
                Element element = (Element) selectElement;
                String methodName = element.attributeValue("id");
                String resultType = element.attributeValue("resultType");
                String queryString = selectElement.getText();
                String key = MethodKeyGenerator.generate(namespace, methodName);
                Mapper mapper = new Mapper();
                mapper.setSqlString(queryString);
                mapper.setResultType(resultType);
                mappers.put(key, mapper);
            }
            return mappers;
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            in.close();
        }
    }

    /**
     * 根据传入的参数，得到mapper中所有被select注解标注的方法。
     * 根据方法名称和类名，以及方法上注解value属性的值，组成Mapper的必要信息
     * @param mapperClassPath
     * @return
     */
    private static Map<String,Mapper> loadMapperAnnotation(String mapperClassPath)throws Exception{
        HashMap<String, Mapper> hashMap = new HashMap<>();
        Class<?> mapperClass = Class.forName(mapperClassPath);
        Method[] methods = mapperClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Select.class)) {
                Select selectAnnotation = method.getAnnotation(Select.class);
                String sqlString = selectAnnotation.value();
                Type type = method.getGenericReturnType();
                Mapper mapper = new Mapper();
                if (type instanceof ParameterizedType) {
                    ParameterizedType pType = (ParameterizedType) type;
                    Type[] types = pType.getActualTypeArguments();
                    Class domainClass = (Class) types[0];
                    String resultType = domainClass.getName();
                    mapper.setResultType(resultType);
                }
                mapper.setSqlString(sqlString);
                String key = MethodKeyGenerator.generate(method.getDeclaringClass().getName(), method.getName());
                hashMap.put(key, mapper);
            }
        }
        return hashMap;
    }







}
