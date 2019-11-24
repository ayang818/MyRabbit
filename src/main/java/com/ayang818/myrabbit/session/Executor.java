package com.ayang818.myrabbit.session;

import com.ayang818.myrabbit.conf.Mapper;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName Executor
 * @Dessription TODO
 * @Author 杨丰畅
 * @Date 2019/11/24 16:34
 **/
public class Executor {

    public static <T> List<T> executeSql(Connection connection, String sqlString, Class<T> resultTypeClass) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sqlString);
            resultSet = preparedStatement.executeQuery();
            List<T> resultList = new LinkedList<>();
            while (resultSet.next()) {
                T resultItem = resultTypeClass.getDeclaredConstructor().newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(columnName);
                    PropertyDescriptor pd = new PropertyDescriptor(columnName, resultTypeClass);
                    Method writeMethod = pd.getWriteMethod();
                    writeMethod.invoke(resultItem, columnValue);
                }
                resultList.add(resultItem);
            }
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(preparedStatement, resultSet);
        }
        return new LinkedList<>();
    }

    public static void release(PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
