package com.atguigu.springboot04.service;

import com.atguigu.springboot04.bean.Goods;
import com.atguigu.springboot04.bean.Kinds;
import com.atguigu.springboot04.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    public int insert(Goods goods){
        int t=goodsMapper.insert(goods);
        return t;
    }
    public int updateGoods(Goods goods){
        return goodsMapper.updateGoods(goods);
    }
    public int getCount(Integer set){
        int t=0;
        if(set==1)
        {
            t=goodsMapper.getcount();
        }
        return t;
    }
    public int getCount(String id,Integer set){
       Map<String,Object> map=new HashMap<String,Object>();
       map.put("id",id);
       map.put("set",set);
        int t=goodsMapper.count(map);
        System.out.println(t);
        return t;
    }
    public List<Goods> getGoodsById(Integer page,Integer size,Integer set){
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("page",page);
        map.put("size",size);
        List<Goods> goods=null;
        if(set==1)
        {
            goods=goodsMapper.getIndexGoods(map);
        }
        return goods;
    }
    public List<Goods> getGoodsById(String id,Integer page,Integer size,Integer set){
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("id",id);
        map.put("page",page);
        map.put("size",size);
        map.put("set",set);
        List<Goods> goods=goodsMapper.getGoodsById(map);
        return goods;
    }
    public int getFileName(String name){
        System.out.println(goodsMapper+" 1");
        String t=goodsMapper.getPicture(name);
        System.out.println(t);
        if(t==null)
        {
            return 0;
        }
        else
        {
            return  1;
        }
    }
    public int updateGoodsof(Integer set,Integer number){
        int t=0;
        if(set==1)
        {
            t=goodsMapper.updateGoodsof1(number);
        }
        if(set==0)
        {
            t=goodsMapper.updateGoodsof3(number);
        }
        return t;
    }
    public Goods getGoodsByNumber(Integer number){
        return goodsMapper.getGoodsByNumber(number);
    }
    public List<Kinds> getKinds(){
        return goodsMapper.getKinds();
    }
}
