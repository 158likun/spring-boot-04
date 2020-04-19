package com.atguigu.springboot04.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.atguigu.springboot04.bean.Orders;
import com.atguigu.springboot04.mapper.OrdersMapper;
import com.atguigu.springboot04.utils.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public void ali(HttpServletResponse response, HttpServletRequest request) throws IOException,AlipayApiException {
        //设置编码
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
        aliPayRequest.setReturnUrl(AlipayConfig.return_url);
        aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，后台可以写一个工具类生成一个订单号，必填
        String order_number = new String((String)request.getParameter("number"));
        System.out.println(order_number+"likun222");
        //付款金额，从前台获取，必填
        String total_amount = new String((String)request.getParameter("amount"));
        System.out.println(total_amount+"likun333");
        //订单名称，必填
        String subject = new String("支付宝沙箱支付（名称随便起）");
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "5m";
        aliPayRequest.setBizContent("{\"out_trade_no\":\"" + order_number + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = alipayClient.pageExecute(aliPayRequest).getBody();
        AlipayConfig.logResult(result);// 记录支付日志
        //输出

        out.println(result);
        //以下写自己的订单代码
       /* int s=new Integer(request.getParameter("number"));

        updateStatusByNumber(2,s-1000);//买方订单状态
        updateSellstatusByNumber(2,s-1000);//卖方订单状态要同时变化*/
    }
    public int insert(String idb,String ids,Integer numberg,String phone,String description,String date){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("idb",idb);
        map.put("ids",ids);
        map.put("numberg",numberg);
        map.put("phone",phone);
        map.put("description",description);
        map.put("date",date);
        return ordersMapper.insert(map);
    }
    public List<Orders> getOrdersById(String id, int l, int r, int set){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",id);
        map.put("l",l);
        map.put("r",r);
        map.put("status",set);
        return ordersMapper.getOrdersById(map);
    }
    public int updateStatusByNumber(int status,int number){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("status",status);
        map.put("number",number);
        return ordersMapper.updateStatusByNumber(map);
    }
    public int getCount(String id,int set){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",id);
        map.put("set",set);
        return ordersMapper.getCount(map);
    }
    public Orders getOrdersByNumber(int number){
        return ordersMapper.getOrdersByNumber(number);
    }
    public int updateSellstatusByNumber(int sell,int number){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("sell",sell);
        map.put("number",number);
        return ordersMapper.updateSellstatusByNumber(map);
    }
    public int getCountBySellstatus(String id,int set){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",id);
        map.put("set",set);
        return ordersMapper.getCountBySellstatus(map);
    }
    public List<Orders> getOrdersBySellstatusAndId(String id, int l, int r, int set){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",id);
        map.put("l",l);
        map.put("r",r);
        map.put("status",set);
        return ordersMapper.getOrdersBySellstatusAndId(map);
    }
}
