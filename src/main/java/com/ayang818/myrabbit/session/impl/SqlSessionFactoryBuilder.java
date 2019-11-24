package com.ayang818.myrabbit.session.impl;

import com.ayang818.myrabbit.conf.Configuration;
import com.ayang818.myrabbit.utils.XMLConfigParser;

import java.io.InputStream;

/**
 * @ClassName SqlSessionFactoryBuilder
 * @Dessription TODO
 * @Author 杨丰畅
 * @Date 2019/11/24 12:22
 **/
public class SqlSessionFactoryBuilder {

    public DefaultSqlSessionFactory build(InputStream configXML) {
        Configuration config = getConfigFromXML(configXML);
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(config);
        return sqlSessionFactory;
    }

    private Configuration getConfigFromXML(InputStream configXML) {
        Configuration config = XMLConfigParser.loadConfiguration(configXML);
        return config;
    }
}
