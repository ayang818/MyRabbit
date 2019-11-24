package com.ayang818.myrabbit.ibatis.session.impl;

import com.ayang818.myrabbit.ibatis.conf.Configuration;
import com.ayang818.myrabbit.ibatis.session.Connectable;
import com.ayang818.myrabbit.ibatis.session.SqlSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName SqlSession
 * @Dessription TODO
 * @Author 杨丰畅
 * @Date 2019/11/24 12:21
 **/
public class DefaultSqlSession implements SqlSession, Connectable {
    private Configuration config;
    private ThreadLocal<Connection> threadLocal;

    public DefaultSqlSession(Configuration config) {
        this.config = config;
    }

    @Override
    public <T> T getMapper(Class<T> mapperCls) {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(config.getDriver());
        Properties properties = new Properties();
        properties.setProperty("username", config.getUsername());
        properties.setProperty("password", config.getPassword());
        ThreadLocal<Connection> threadLocal = ThreadLocal.withInitial(() -> {
            try {
                return DriverManager.getConnection(config.getUrl(), properties);
            } catch (SQLException e) {
                return null;
            }
        });
        return threadLocal.get();
    }
}
