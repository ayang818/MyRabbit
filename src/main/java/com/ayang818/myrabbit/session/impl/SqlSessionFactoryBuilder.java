package com.ayang818.myrabbit.session.impl;

import com.ayang818.myrabbit.conf.Configuration;
import com.ayang818.myrabbit.utils.XMLConfigParser;

import java.io.InputStream;

/**
 * @ClassName SqlSessionFactoryBuilder
 * @Description TODO
 * @Author 杨丰畅
 * @Date 2019/11/24 12:22
 **/
public class SqlSessionFactoryBuilder {

    public DefaultSqlSessionFactory build(InputStream configXML) {
        Configuration config = getConfigFromXML(configXML);
        return new DefaultSqlSessionFactory(config);
    }

    private Configuration getConfigFromXML(InputStream configXML) {
        return XMLConfigParser.loadConfiguration(configXML);
    }
}
