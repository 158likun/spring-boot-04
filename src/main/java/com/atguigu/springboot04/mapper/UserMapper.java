package com.atguigu.springboot04.mapper;

import java.util.List;

import com.atguigu.springboot04.bean.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Results({ //2 @Results是结果映射列表，@Result中property是User类的属性名，colomn是数据库表的字段名
            @Result(property = "id", column = "id"), //2
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password")
    })
    @Select({"SELECT * FROM USER"})
    User findAll();
    @Select({"SELECT * FROM USER WHERE ID = #{id}"})
    User findById(@Param("id") String id);

    @Insert({"INSERT INTO USER(ID,NAME, PASSWORD) VALUES(#{id},#{name},#{password})"})
    int insert(User user);

    @Update({"UPDATE USER SET  PASSWORD=#{password} WHERE ID=#{id}"})
    int update(@Param("password") String password, @Param("id") String id);
}
