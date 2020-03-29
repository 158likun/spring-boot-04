package com.atguigu.springboot04.service;

import com.atguigu.springboot04.bean.Goods;

import java.util.List;

public interface GoodsService {
    public int insert(Goods goods);
    public int updateGoods(Goods goods);
    public int getCount(String id,Integer set);
    public List<Goods> getGoodsById(String id,Integer page,Integer size,Integer set);
    public int getFileName(String name);
    public int updateGoodsof(Integer set,Integer number);
    public Goods getGoodsByNumber(Integer number);
}
