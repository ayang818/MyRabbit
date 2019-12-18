package com.ayang818.myrabbit.session.impl;

import com.ayang818.myrabbit.conf.Mapper;
import com.ayang818.myrabbit.session.Execute;

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
 * @Description TODO
 * @Author 杨丰畅
 * @Date 2019/11/24 16:34
 **/
public class Executor implements Execute {

    @Override
    public <T> List<T> selectList(Connection connection, Mapper mapper, Class<?> resultTypeClass) {
        String sqlString = mapper.getSqlString();
        Statement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.createStatement();
            resultSet = preparedStatement.executeQuery(sqlString);
            List<T> resultList = new LinkedList<>();
            while (resultSet.next()) {
                T resultItem = (T) resultTypeClass.getDeclaredConstructor().newInstance();
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

    @Override
    public <T> T selectOne() {
        return null;
    }

    @Override
    public int delete(Connection connection, Mapper mapper) {
        String sqlString = mapper.getSqlString();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            return statement.executeUpdate(sqlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insert(Connection connection, Mapper mapper) {
        String sqlString = mapper.getSqlString();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            return statement.executeUpdate(sqlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Connection connection, Mapper mapper) {
        String sqlString = mapper.getSqlString();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            return statement.executeUpdate(sqlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void release(Statement ps, ResultSet rs) {
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
