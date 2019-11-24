package com.ayang818.myrabbit.ibatis.session.impl;

import com.ayang818.myrabbit.ibatis.conf.Configuration;
import com.ayang818.myrabbit.ibatis.session.SqlSessionFactory;
import com.ayang818.myrabbit.ibatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * @ClassName SqlSessionFactoryBuilder
 * @Dessription TODO
 * @Author 杨丰畅
 * @Date 2019/11/24 12:22
 **/
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream configXML) {
        Configuration config = getConfigFromXML(configXML);
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(config);
        return sqlSessionFactory;
    }

    private Configuration getConfigFromXML(InputStream configXML) {
        Configuration config = XMLConfigBuilder.loadConfiguration(configXML);
        return config;
    }
}
