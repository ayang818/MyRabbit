package com.ayang818.myrabbit.session;

import com.ayang818.myrabbit.session.impl.DefaultSqlSession;

public interface SqlSessionFactory {
    DefaultSqlSession openSession();
}
