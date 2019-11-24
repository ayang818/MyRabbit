package com.ayang818.myrabbit.ibatis.io;

import java.io.InputStream;

/**
 * @ClassName Resourses
 * @Dessription TODO
 * @Author 杨丰畅
 * @Date 2019/11/24 12:18
 **/
public class Resources {
    public static InputStream getResourceAsStream(String filepath) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(filepath);
    }
}
