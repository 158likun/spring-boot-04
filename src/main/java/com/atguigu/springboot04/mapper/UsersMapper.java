package com.atguigu.springboot04.mapper;

import com.atguigu.springboot04.bean.Users;
import org.apache.ibatis.annotations.*;
@Mapper
public interface UsersMapper {
    @Results({ //2 @Results是结果映射列表，@Result中property是Users类的属性名，colomn是数据库表的字段名
            @Result(property = "id", column = "id"), //2
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "password", column = "password"),
            @Result(property = "description", column = "description")
    })
    @Select({"SELECT * FROM USERS"})
    Users findAll();
    @Select({"SELECT * FROM USERS WHERE ID = #{id}"})
    Users findById(@Param("id") String id);

    @Insert({"INSERT INTO USERS(ID,NAME,SEX,PASSWORD,DESCRIPTION) VALUES(#{id},#{name},#{sex},#{password},#{description})"})
    int insert(Users user);

    @Update({"UPDATE USERS SET  NAME=#{name},PASSWORD=#{password},SEX=#{sex},DESCRIPTION={description} WHERE ID=#{id}"})
    int update(@Param("name") String name,@Param("password") String password,@Param("sex") String sex,@Param("id") String id);
}
