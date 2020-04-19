package com.atguigu.springboot04.service;

import com.alipay.api.AlipayApiException;
import com.atguigu.springboot04.bean.Orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public interface OrdersService {
    public void ali(HttpServletResponse response, HttpServletRequest request) throws IOException,AlipayApiException;
    public int insert(String idb,String ids,Integer numberg,String phone,String description,String date);
    public List<Orders> getOrdersById(String id,int l,int r,int set);
    public int updateStatusByNumber(int status,int number);
    public int getCount(String id,int set);
    public Orders getOrdersByNumber(int number);
    public int updateSellstatusByNumber(int sell,int number);
    public int getCountBySellstatus(String id,int set);
    public List<Orders> getOrdersBySellstatusAndId(String id, int l, int r, int set);
}
