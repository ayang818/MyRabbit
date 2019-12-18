package com.ayang818.myrabbit.utils;

/**
 * @ClassName MethodKeyGenerator
 * @Description TODO
 * @Author 杨丰畅
 * @Date 2019/11/24 16:27
 **/
public class MethodKeyGenerator {
    public static String generate(String className, String methodName) {
        return className + "." + methodName;
    }
}
