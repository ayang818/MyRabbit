package com.ayang818.myrabbit.mapper;

import com.ayang818.myrabbit.ibatis.annotation.Select;
import com.ayang818.myrabbit.model.User;


import java.util.List;

/**
* @Date 21:22 2019/11/23
* @Author 杨丰畅
* @Description //TODO 
* @Param 
* @Return 
*/
public interface UserMapper {
    @Select("select * from tbl_user limit 10 offset 0")
    List<User> findAll();
}
