package com.atguigu.springboot04.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

//sqlSessionFactory-->sqlSession
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;
    static{
        try{
            //使用mybatis的第一步，获取sqlSessionFactory的对象
            String resource="application.yml";
            InputStream inputStream= Resources.getResourceAsStream(resource);
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        }catch(IOException e){
            e.printStackTrace();
        }
    }   //第二步获得SqlSession实例，它包括了面向数据库执行sql语句的方法
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
