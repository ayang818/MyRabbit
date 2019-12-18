package com.ayang818.myrabbit.example.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName User
 * @Description TODO
 * @Author 杨丰畅
 * @Date 2019/11/23 21:09
 **/
@Data
public class User {
    private Integer id;
    private String username;
    private String email;
    private Integer age;
    private Integer type;
    private Date create_time;
}
