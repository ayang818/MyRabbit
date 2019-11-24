package com.ayang818.myrabbit.example.mapper;

import com.ayang818.myrabbit.io.Resources;
import com.ayang818.myrabbit.session.impl.DefaultSqlSession;
import com.ayang818.myrabbit.session.SqlSessionFactory;
import com.ayang818.myrabbit.session.impl.SqlSessionFactoryBuilder;
import com.ayang818.myrabbit.example.model.User;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName MybatisTest
 * @Dessription TODO
 * @Author 杨丰畅
 * @Date 2019/11/23 21:44
 **/
public class MybatisTest {

    @Test
    public void queryTest() throws IOException {
//        使用sqlSession创建mapper的动态代理
//        InputStream in = Resources.getResourceAsStream("SqlMapperConfig.xml");
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        DefaultSqlSession sqlSession = sessionFactory.openSession();
        // 用动态代理增强
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
        resourceAsStream.close();
    }
}
