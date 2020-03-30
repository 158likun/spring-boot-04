package com.atguigu.springboot04.mapper;

import com.atguigu.springboot04.bean.Goods;
import com.atguigu.springboot04.bean.Kinds;
import com.atguigu.springboot04.bean.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsMapper {
    int insert(Goods goods);
    int updateGoods(Goods goods);
    int getcount();
    int count(Map<String,Object> map);
    List<Goods> getGoodsById(Map<String,Object> map);
    List<Goods> getIndexGoods(Map<String,Object> map);
    String getPicture(String t);
    int updateGoodsof1(int number);
    int updateGoodsof3(int number);
    Goods getGoodsByNumber(int number);
    List<Kinds> getKinds();
}
