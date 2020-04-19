package com.atguigu.springboot04.mapper;

import com.atguigu.springboot04.bean.Cars;
import com.atguigu.springboot04.bean.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarsMapper {
    List<Cars> getCars(Map map);
    int update(int number);
    int updateByGood(Map<String,Object> map);
    int getCount(String id);
    int updateAll(String id);
    int updateByNumber(int number);
    int insert(Map<String,Object> map);
    Cars getCarsByNumber(Map<String,Object> map);
    List<Cars> getCarsNumberById(String id);
}
