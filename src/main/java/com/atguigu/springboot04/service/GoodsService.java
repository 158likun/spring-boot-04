package com.atguigu.springboot04.service;

import com.atguigu.springboot04.bean.Goods;

public interface GoodsService {
    public int insert(Goods goods);
    public int getCount();
    public int getFileName(String name);
}
