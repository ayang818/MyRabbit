package com.ayang818.myrabbit.conf;

import lombok.Data;

import java.util.Map;

/**
 * @ClassName Configuration
 * @Description TODO
 * @Author 杨丰畅
 * @Date 2019/11/24 13:25
 **/
@Data
public class Configuration {
    private String username;
    private String password;
    private String url;
    private String driver;
    private Map<String, Mapper> map;
}
