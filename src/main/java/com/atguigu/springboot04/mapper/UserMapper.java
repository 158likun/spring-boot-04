package com.atguigu.springboot04.mapper;

import java.util.List;

import com.atguigu.springboot04.bean.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

   /* @Select("SELECT * FROM USER")
    List<User> findAll();*/
    @Select("SELECT * FROM USER WHERE ID = #{id}")
    User findById(@Param("id") String id);

    @Insert("INSERT INTO USER(ID,NAME, PASSWORD) VALUES(#{id},#{name},#{password})")
    int insert(User user);

    @Update("UPDATE USER SET  PASSWORD=#{password} WHERE ID=#{id}")
    int update(@Param("password") String password, @Param("id") String id);
}
