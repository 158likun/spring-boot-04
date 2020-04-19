package com.atguigu.springboot04.mapper;

import com.atguigu.springboot04.bean.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrdersMapper {
    int insert(Map<String,Object> map);
    List<Orders> getOrdersById(Map<String,Object> map);
    int updateStatusByNumber(Map<String,Object> map);
    int getCount(Map<String,Object> map);
    Orders getOrdersByNumber(int number);
    int updateSellstatusByNumber(Map<String,Object> map);
    int getCountBySellstatus(Map<String,Object> map);
    List<Orders> getOrdersBySellstatusAndId(Map<String,Object> map);
}
