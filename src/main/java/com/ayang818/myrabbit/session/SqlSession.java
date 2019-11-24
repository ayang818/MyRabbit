package com.ayang818.myrabbit.session;

public interface SqlSession {
    <T> T getMapper(Class<T> mapperClass) throws ClassNotFoundException;
    void close();
}
