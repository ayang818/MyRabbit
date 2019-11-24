package com.ayang818.myrabbit.utils;

import com.ayang818.myrabbit.ibatis.io.Resources;
import com.ayang818.myrabbit.ibatis.session.SqlSessionFactory;
import com.ayang818.myrabbit.ibatis.session.impl.DefaultSqlSessionFactory;
import com.ayang818.myrabbit.ibatis.session.impl.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * @ClassName ParseXMLTest
 * @Dessription TODO
 * @Author 杨丰畅
 * @Date 2019/11/24 15:05
 **/
public class ParseXMLTest {

    @Test
    public void parseXMLDataTest() {
        InputStream in = Resources.getResourceAsStream("sqlMapperConfig.xml");
        DefaultSqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        System.out.println(sqlSessionFactory.getConfig());
    }
}
