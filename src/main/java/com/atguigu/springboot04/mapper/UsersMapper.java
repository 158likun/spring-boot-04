package com.atguigu.springboot04.mapper;

import com.atguigu.springboot04.bean.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersMapper {
    List<Users> findAll();
    Users findById(String id);
    //通过密码和账号查找
    Users find(Map<String,Object> map);
    //注册
    int insert(Users users);
    int update(Users users);
}
