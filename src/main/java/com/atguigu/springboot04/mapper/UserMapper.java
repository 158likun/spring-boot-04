package com.atguigu.springboot04.mapper;

import java.util.List;

import com.atguigu.springboot04.bean.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    List<User> findAll();
    User findById(String id);
    int insert(User user);
    int update(String password,String id);
}
