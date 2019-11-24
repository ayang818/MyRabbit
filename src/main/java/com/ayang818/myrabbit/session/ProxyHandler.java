package com.ayang818.myrabbit.session;

import com.ayang818.myrabbit.conf.Configuration;
import com.ayang818.myrabbit.conf.Mapper;
import com.ayang818.myrabbit.utils.MethodKeyGenerator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ProxyHandler
 * @Dessription TODO
 * @Author 杨丰畅
 * @Date 2019/11/24 16:16
 **/
public class ProxyHandler implements InvocationHandler {
    private Class target;
    private Map<String, Mapper> methodMap;
    private Connection connection;

    @SuppressWarnings("unchecked warning")
    public <T> T bind(Class<T> target, Map methodMap, Connection connection) throws ClassNotFoundException {
        this.target = target;
        this.methodMap = methodMap;
        this.connection = connection;
        return (T) Proxy.newProxyInstance(target.getClassLoader(), new Class[] {target}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String key = MethodKeyGenerator.generate(method.getDeclaringClass().getName(), method.getName());
        Mapper mapper = methodMap.get(key);
        String className = mapper.getResultType();
        Class<?> returnType = Class.forName(className);
        List<?> linkList = Executor.executeSql(connection, mapper.getSqlString(), returnType);
        List<Object> resList = new ArrayList<>();
        resList.addAll(linkList);
        return resList;
    }
}
