package com.ayang818.myrabbit.ibatis.session;

public interface SqlSession {
    <T> T getMapper(Class<T> mapperClass);
    void close();
}
