package com.ayang818.myrabbit.session;

import com.ayang818.myrabbit.conf.Mapper;
import com.ayang818.myrabbit.enums.SqlTypeConsts;
import com.ayang818.myrabbit.session.impl.Executor;
import com.ayang818.myrabbit.utils.MethodKeyGenerator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ProxyHandler
 * @Dessription TODO
 * @Author 杨丰畅
 * @Date 2019/11/24 16:16
 **/
public class ProxyHandler implements InvocationHandler {
    private Map<String, Mapper> methodMap;
    private Connection connection;

    @SuppressWarnings("unchecked warning")
    public <T> T bind(Class<T> target, Map methodMap, Connection connection) throws ClassNotFoundException {
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
        Executor executor = new Executor();
        if (SqlTypeConsts.SELECT.equals(mapper.getSqlType())) {
            List<?> linkList = executor.selectList(connection, mapper, returnType);
            List<Object> resList = new ArrayList<>();
            resList.addAll(linkList);
            return resList;
        }
        return null;
    }
}
