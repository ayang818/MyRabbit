package com.ayang818.myrabbit.session;

import com.ayang818.myrabbit.conf.Mapper;

import java.sql.Connection;
import java.util.List;

public interface Execute {
    <T> List<T> selectList(Connection connection, Mapper mapper, Class<?> resultTypeClass);

    <T> T selectOne();

    int delete(Connection connection, Mapper mapper);

    int insert(Connection connection, Mapper mapper);

    int update(Connection connection, Mapper mapper);
}
