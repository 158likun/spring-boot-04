package com.atguigu.springboot04.mapper;

import com.atguigu.springboot04.bean.Goods;
import com.atguigu.springboot04.bean.Users;
import org.apache.ibatis.annotations.*;
@Mapper
public interface GoodsMapper {
    int insert(Goods goods);
    int count();
    String getPicture(String t);
}
