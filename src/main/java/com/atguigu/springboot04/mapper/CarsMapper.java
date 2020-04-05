package com.atguigu.springboot04.mapper;

import com.atguigu.springboot04.bean.Cars;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarsMapper {
    List<Cars> getCars(Map map);
    int update(int number);
    int getCount(String id);
    int updateAll(String id);
    int updateByNumber(int number);;
}
