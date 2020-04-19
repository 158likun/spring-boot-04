package com.atguigu.springboot04.service;


import com.atguigu.springboot04.bean.Cars;
import com.atguigu.springboot04.bean.Goods;

import java.util.List;
import java.util.Map;

public interface CarsService {
    List<Cars> getCars(String id,int l,int r);
    int update(int number);
    int updateByGood(String idb,int number);
    int getCount(String id);
    int updateAll(String id);
    int updateByNumber(int number);
    Cars getCarsByNumber(String id,Integer number);
    int insert(String idb,String ids,Integer number);
    List<Cars> getCarsNumberById(String id);
}
