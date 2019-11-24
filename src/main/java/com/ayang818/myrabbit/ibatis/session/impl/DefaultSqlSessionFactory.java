package com.ayang818.myrabbit.ibatis.session.impl;

import com.ayang818.myrabbit.ibatis.conf.Configuration;
import com.ayang818.myrabbit.ibatis.session.SqlSessionFactory;

/**
 * @ClassName SqlSessionFactory
 * @Dessription TODO
 * @Author 杨丰畅
 * @Date 2019/11/24 12:21
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration config;

    public DefaultSqlSessionFactory(Configuration config) {
        this.config = config;
    }

    @Override
    public DefaultSqlSession openSession() {
        DefaultSqlSession session = new DefaultSqlSession(config);
        return session;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }
}
