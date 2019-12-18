package com.ayang818.myrabbit.session.impl;

import com.ayang818.myrabbit.conf.Configuration;
import com.ayang818.myrabbit.session.DefaultConnection;
import com.ayang818.myrabbit.session.ProxyHandler;
import com.ayang818.myrabbit.session.SqlSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName SqlSession
 * @Description TODO
 * @Author 杨丰畅
 * @Date 2019/11/24 12:21
 **/
public class DefaultSqlSession implements SqlSession {
    private Configuration config;

    public DefaultSqlSession(Configuration config) {
        this.config = config;
    }

    @Override
    public <T> T getMapper(Class<T> mapperCls) throws ClassNotFoundException {
        System.out.println();
        Connection connection = DefaultConnection.getConnection(config);
        ProxyHandler proxyHandler = new ProxyHandler();
        return proxyHandler.bind(mapperCls, config.getMap(), connection);
    }

    @Override
    public void close() {

    }
}
