package com.atguigu.springboot04.mapper;

import com.atguigu.springboot04.bean.User;
import com.atguigu.springboot04.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

public class UserMapperTest {
    @Test
    public void test(){
        //获得SqlSession对象
        SqlSession sqlSession= MybatisUtils.getSqlSession();
        //执行SQL
        //方法一
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        User user=userMapper.findById("B20160304424");
        //方法二
        //List<User> userList=sqlSession.selectList("com.atguigu.springboot04.mapper.UserMapper.findAll");
        System.out.println(user);
        //增、删、改必须插入事务
       // sqlSession.commit();
        //关闭SqlSession
        sqlSession.close();
    }

}
