package com.atguigu.springboot04.service;

import com.atguigu.springboot04.bean.Goods;
import com.atguigu.springboot04.bean.Kinds;

import java.util.List;

public interface GoodsService {
    public int insert(Goods goods);
    public int updateGoods(Goods goods);
    public int updateOrigin(Goods goods);
    public int getCount(String id,Integer set);
    public int getCountAll(Integer set);
    public int getCountByKind(String kind);
    public int getCountByCheck(String check);
    public List<Goods> getGoodsByKinds(String kind,Integer l,Integer r);
    public List<Goods> getGoodsById(Integer page,Integer size,Integer set);
    public List<Goods> getGoodsById(String id,Integer page,Integer size,Integer set);
    public int getFileName(String name);
    public int updateGoodsof(Integer set,Integer number);
    public Goods getGoodsByNumber(Integer number);
    public List<Kinds> getKinds();
    public List<String> getSecondKinds();
    public List<Goods> getRecommends(Goods g);
    public List<Goods> getGoodsByCheck(String check,Integer l,Integer r);
}
