package com.ayang818.myrabbit.ibatis.session;

import java.sql.Connection;
import java.sql.SQLException;

public interface Connectable {
    Connection getConnection() throws ClassNotFoundException, SQLException;
}
