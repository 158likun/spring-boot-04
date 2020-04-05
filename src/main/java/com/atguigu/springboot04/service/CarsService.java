package com.atguigu.springboot04.service;


import com.atguigu.springboot04.bean.Cars;

import java.util.List;

public interface CarsService {
    List<Cars> getCars(String id,int l,int r);
    int update(int number);
    int getCount(String id);
    int updateAll(String id);
    int updateByNumber(int number);
}
