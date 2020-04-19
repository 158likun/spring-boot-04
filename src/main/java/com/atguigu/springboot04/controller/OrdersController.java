package com.atguigu.springboot04.controller;

import com.alipay.api.AlipayApiException;
import com.atguigu.springboot04.bean.Cars;
import com.atguigu.springboot04.bean.Goods;
import com.atguigu.springboot04.bean.Orders;
import com.atguigu.springboot04.bean.Users;
import com.atguigu.springboot04.dto.OrdersPage;
import com.atguigu.springboot04.dto.Page;
import com.atguigu.springboot04.dto.PutAway;
import com.atguigu.springboot04.service.CarsService;
import com.atguigu.springboot04.service.GoodsService;
import com.atguigu.springboot04.service.OrdersService;
import com.atguigu.springboot04.utils.DateTrans;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private CarsService carsService;
    //alipay支付功能
    @RequestMapping(value = "/ali",method = RequestMethod.GET)
    public void ali(HttpServletResponse response, HttpServletRequest request) throws IOException, AlipayApiException {
        ordersService.ali(response,request);
    }
    //alipay同步url
    @RequestMapping(value = "/pay",method = RequestMethod.GET)
    public String pay(HttpServletRequest request,Model model){//查询文档同步url怎样传参
        int s=(int)request.getSession().getAttribute("pay");
        int set=(int)request.getSession().getAttribute("set");
        int page=(int)request.getSession().getAttribute("page");
        ordersService.updateStatusByNumber(2,s);//买方订单状态
        ordersService.updateSellstatusByNumber(2,s);
        return "redirect:/ordersmanage?set="+set+"&page="+page;
    }
    //订单详情页面点击提交订单按钮
    @RequestMapping(value = "/addorders",method = RequestMethod.GET)
    public String addOrders(Model model,
                            HttpServletRequest request,
                            @RequestParam(name="number",defaultValue = "-1") Integer number,//商品编号
                            @RequestParam(name="set") Integer set,
                            @RequestParam(name="phone",defaultValue = "") String phone,//联系电话
                            @RequestParam(name="description",defaultValue = "") String description//备注
                                ){
        //1商品详情页面购买，2shoopingcar
        if(set==1||set==2)
        {
            Goods goods=goodsService.getGoodsByNumber(number);
            //长期处于商品详情页面，但该商品已下架时，点击任何事件
            if(goods.getUdstatus()!=1||goods.getDeletstatus()==1)
            {
                return "redirect:/index";
            }
            Users o=(Users) request.getSession().getAttribute("users");
            if(o==null)
            {
                return "redirect:/login?number="+number;
            }
            if(set==2)
            {
                carsService.updateByGood(o.getId(),number);
            }
            goodsService.updateByNumber(number);
            ordersService.insert(o.getId(),goods.getId(),number,phone,description, DateTrans.getSystemDate());
            return "redirect:/index";
        }
        else
        {
            //一键购买车
            Users users=(Users) request.getSession().getAttribute("users");
            if(users==null)
            {
                return "redirect:/index";
            }
            List<Cars> list=carsService.getCarsNumberById(users.getId());
            Goods in=null;
            int t=0;
            for(Cars c: list){
                in=goodsService.getGoodsByNumber(c.getGood());
                if(in.getUdstatus()!=1)
                {
                    t=1;
                    break;
                }
            }
            if(t==1)
            {
                return "redirect:/index";
            }
            else
            {
                for(Cars c: list){
                    in=goodsService.getGoodsByNumber(c.getGood());
                    goodsService.updateByNumber(in.getNumber());
                    ordersService.insert(users.getId(),in.getId(),number,phone,description,DateTrans.getSystemDate());
                }
                carsService.updateAll(users.getId());
                return "redirect:/index";
            }
        }
    }
    //订单管理页面点击链接或商品管理功能中的订单操作
    @RequestMapping(value = "/ordersmanage",method = RequestMethod.GET)
    public String upGoods(HttpServletRequest request,
                          Model model,
                          @RequestParam(name="page",defaultValue = "1") Integer page,//但前要访问的页面
                          @RequestParam(name="set",defaultValue = "1") Integer set,//标记来自不同页面的请求
                          @RequestParam(name="number",defaultValue = "-1") Integer number,//各自页面中当前操作的订单number
                          @RequestParam(name="size",defaultValue = "2") Integer size){//每页所包含的数据数目
        Object o=request.getSession().getAttribute("users");
        if(o==null)
        {
            return "redirect:/login";
        }
        if(set==1)
        {
            if(number!=-1)
            {
                //已完成订单页面点击删除
                ordersService.updateStatusByNumber(0,number);
            }
            OrdersPage t=common(o,page,size,set);
            model.addAttribute("orderspage",t);
            return "ordersfinish";
        }
        if(set==2)
        {
            if(number!=-1)
            {
                //待收货页面点击退货
                Orders orders=ordersService.getOrdersByNumber(number);
                if(orders.getStatus()!=1)
                {
                    return "redirect:/ordersmanage?page="+page+"&set="+set+"&erro="+ URLEncoder.encode("该订单已处理！");
                }
                else
                {
                    ordersService.updateStatusByNumber(0,number);//买方订单状态
                    ordersService.updateSellstatusByNumber(0,number);//卖方订单状态要同时变化
                    goodsService.updateStatusByNumber(orders.getNumberg());//商品状态
                }
            }
            OrdersPage t=common(o,page,size,set);
            model.addAttribute("orderspage",t);
            return "orders";
        }
        if(set==3)
        {
            //待收货页面点击收货
            Orders orders=ordersService.getOrdersByNumber(number);
            if(orders.getStatus()!=1)
            {
                return "redirect:/ordersmanage?page="+page+"&set="+2+"&erro="+ URLEncoder.encode("该订单已处理！");
            }
            else
            {
                /*ordersService.updateStatusByNumber(2,number);//买方订单状态
                ordersService.updateSellstatusByNumber(2,number);//卖方订单状态要同时变化
                */
                Goods goods=goodsService.getGoodsByNumber(orders.getNumberg());
                request.getSession().setAttribute("pay",number);
                request.getSession().setAttribute("page",page);
                request.getSession().setAttribute("set",set);
                return "redirect:/ali?number="+(number+1100)+"&amount="+goods.getPrice();//1100+number为订单编号
            }
        }
        if(set==4)
        {
            if(number!=-1)
            {
                //买方已付款商品订单页面点击删除
                ordersService.updateSellstatusByNumber(0,number);
            }
            OrdersPage t=common(o,page,size,set);
            model.addAttribute("orderspage",t);
            return "selledgoods";
        }
        if(set==5)
        {
            if(number!=-1)
            {
                //买方未付款商品订单页面点击取消订单
                Orders orders=ordersService.getOrdersByNumber(number);
                if(orders.getSellstatus()!=1)
                {
                    return "redirect:/ordersmanage?page="+page+"&set="+set+"&erro="+ URLEncoder.encode("该订单已处理！");
                }
                else
                {
                    ordersService.updateStatusByNumber(0,number);//买方订单状态
                    ordersService.updateSellstatusByNumber(0,number);//卖方订单状态要同时变化
                    goodsService.updateStatusByNumber(orders.getNumberg());//商品状态
                }
            }
            OrdersPage t=common(o,page,size,set);
            model.addAttribute("orderspage",t);
            return "nosellgoods";
        }
        return "redirect:/index";
    }
    public OrdersPage common(Object o,Integer page,Integer size,Integer set){
        Users users=(Users)o;
        String id=users.getId();
        int j=2;
        int s=0;
        if(set<=3&&set>=1)
        {
            if(set==2)
            {
                j=1;
            }
            s=ordersService.getCount(id,j);
        }
        else
        {
            if(set==5)
            {
                j=1;
            }
            s=ordersService.getCountBySellstatus(id,j);
        }
        OrdersPage t=new OrdersPage();
        t.setPageInation(s,page,size);
        List<Orders> list=null;
        if(set<=3&&set>=1)
        {
            if(t.getPage()>=1)
            {
                list=ordersService.getOrdersById(id,((t.getPage()-1)*size),size,j);
            }
        }
        else
        {
            if(t.getPage()>=1)
            {
                list=ordersService.getOrdersBySellstatusAndId(id,((t.getPage()-1)*size),size,j);
            }
        }
//      for(Integer h:t.getPages()){
//          System.out.println(h+" ");
//      }
        t.setOrders(list);
        return t;
    }
}
