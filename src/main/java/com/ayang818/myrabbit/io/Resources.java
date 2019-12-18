package com.ayang818.myrabbit.io;

import java.io.InputStream;

/**
 * @ClassName Resourses
 * @Description TODO
 * @Author 杨丰畅
 * @Date 2019/11/24 12:18
 **/
public class Resources {
    public static InputStream getResourceAsStream(String filepath) {
        return Resources.class.getClassLoader().getResourceAsStream(filepath);
    }
}
