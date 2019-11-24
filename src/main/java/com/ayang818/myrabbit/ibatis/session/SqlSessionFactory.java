package com.ayang818.myrabbit.ibatis.session;

import com.ayang818.myrabbit.ibatis.session.impl.DefaultSqlSession;

public interface SqlSessionFactory {
    DefaultSqlSession openSession();
}
