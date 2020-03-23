package com.atguigu.springboot04.service;

import com.atguigu.springboot04.bean.Goods;
import com.atguigu.springboot04.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    public int insert(Goods goods){
        int t=goodsMapper.insert(goods);
        return t;
    }
    public int getCount(){
        int t=goodsMapper.count();
        System.out.println(t);
        return t;
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
}
