package com.atguigu.springboot04.mapper;

import com.atguigu.springboot04.bean.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsersMapper {

    @Select("SELECT * FROM USERS")
    List<Users> findAll();
    @Select("SELECT * FROM USERS WHERE ID = #{id}")
    Users findById(@Param("id") String id);

    @Insert("INSERT INTO USERS(ID,NAME,SEX,PASSWORD,DESCRIPTION) VALUES(#{id},#{name},#{sex},#{password},#{description})")
    int insert(Users user);

    @Update("UPDATE USERS SET  NAME=#{name},PASSWORD=#{password},SEX=#{sex},DESCRIPTION={description} WHERE ID=#{id}")
    int update(@Param("name") String name,@Param("password") String password,@Param("sex") String sex,@Param("id") String id);
}
