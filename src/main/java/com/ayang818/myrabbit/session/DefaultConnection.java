package com.ayang818.myrabbit.session;

import com.ayang818.myrabbit.conf.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName DefaultConnection
 * @Description TODO
 * @Author 杨丰畅
 * @Date 2019/11/24 16:02
 **/
public class DefaultConnection {
    private static ThreadLocal<Connection> connectHolder;

    public static Connection getConnection(Configuration config) {
        if (connectHolder == null) {
            connectHolder = ThreadLocal.withInitial(() -> {
                try {
                    Class.forName(config.getDriver());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Properties properties = new Properties();
                properties.setProperty("user", config.getUsername());
                properties.setProperty("password", config.getPassword());
                try {
                    return DriverManager.getConnection(config.getUrl(), properties);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            });
        }
        return connectHolder.get();
    }
}
