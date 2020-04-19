package com.atguigu.springboot04.controller;

import com.atguigu.springboot04.bean.Cars;
import com.atguigu.springboot04.bean.Goods;
import com.atguigu.springboot04.bean.Users;
import com.atguigu.springboot04.bean.Words;
import com.atguigu.springboot04.dto.CarsInformation;
import com.atguigu.springboot04.dto.Page;
import com.atguigu.springboot04.dto.PutAway;
import com.atguigu.springboot04.dto.ShoopingCar;
import com.atguigu.springboot04.service.CarsService;
import com.atguigu.springboot04.service.GoodsService;
import com.atguigu.springboot04.service.UsersService;
import com.atguigu.springboot04.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CarsController {
    @Autowired
    private CarsService carsService;
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private WordsService wordsService;
    //与商品管理有关的请求
    @RequestMapping(value = "/shoppingcar",method = RequestMethod.GET)
    public String shoppingCar(HttpServletRequest request,
                          Model model,
                          @RequestParam(name="page",defaultValue = "1") Integer page,//但前要访问的页面
                          @RequestParam(name="set",defaultValue = "1") Integer set,//标记来自不同页面的请求
                          @RequestParam(name="number",defaultValue = "-1") Integer number,//各自页面中当前操作的商品number
                          @RequestParam(name="size",defaultValue = "1") Integer size){//每页所包含的数据数目

        System.out.println(set+"哈哈1");
        Object o=request.getSession().getAttribute("users");
        if(o==null)
        {
            return "redirect:/login";
        }
        if(set==1)
        {
            String erro=request.getParameter("erro");
            if(erro!=null)
            {
                try {
                    model.addAttribute("erro",erro);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            ShoopingCar t=common(o,page,size,set);
            model.addAttribute("carInfor",t);
            return "shoppingcar";
        }
        if(set==5)
        {
            System.out.println(((Users)o).getId()+"All123");
            carsService.updateAll(((Users)o).getId());
            ShoopingCar t=common(o,page,size,set);
            model.addAttribute("carInfor",t);
            return "shoppingcar";
        }
        if(set==3)
        {
            carsService.updateByNumber(number);
            System.out.println(((Users)o).getId()+"number123");
            ShoopingCar t=common(o,page,size,set);
            model.addAttribute("carInfor",t);
            return "shoppingcar";
        }
        return "shoppingcar";
    }
    public ShoopingCar common(Object o, Integer page, Integer size, Integer set){
        Users user=(Users)o;
        String id=user.getId();
        int s=carsService.getCount(id);
        ShoopingCar t=new ShoopingCar();
        t.setPageInation(s,page,size);
        List<CarsInformation> list=null;
        List<Cars> cars=null;
        if(t.getPage()>=1)
        {
            cars=carsService.getCars(id,((t.getPage()-1)*size),size);
            if(cars!=null)
            {
                list=new ArrayList<CarsInformation>();
                for(Cars car: cars)
                {
                    CarsInformation c=new CarsInformation();
                    c.setCar(car);
                    c.setGood(goodsService.getGoodsByNumber(car.getGood()));
                    c.setUsers(usersService.getUsers(car.getIds()));
                    list.add(c);
                }
            }
        }
        t.setCarsInformation(list);
        return t;
    }
    @RequestMapping(value = "/addshoppingcar",method = RequestMethod.GET)
    public String addcar(HttpServletRequest request,
                         Model model,
                         @RequestParam(name="number",defaultValue = "-1") Integer number,//各自页面中当前操作的商品number
                         @RequestParam(name="set") Integer set,
                         @RequestParam(name="page",defaultValue = "1") Integer page//购物车的页面
                        ){
        Users users=(Users) request.getSession().getAttribute("users");
        if(set==1)
        {
            Goods goods=goodsService.getGoodsByNumber(number);
            //长期处于商品详情页面，但该商品已下架时，点击任何事件
            if(goods.getUdstatus()!=1||goods.getDeletstatus()==1)
            {
                return "redirect:/index";
            }
            if(users==null)
            {
                return "redirect:/login?number="+number;
            }
            Goods g=goodsService.getGoodsByNumber(number);
            model.addAttribute("goodpage",g);
            model.addAttribute("all",0);
            List<Words> words=wordsService.getWords(number);
            model.addAttribute("comment",words);
            List<Goods> recommends=goodsService.getRecommends(g);
            model.addAttribute("recommends",recommends);
            Cars c=carsService.getCarsByNumber(users.getId(),number);
            if(c==null)
            {
                carsService.insert(users.getId(),g.getId(),number);
            }
            else
            {
                model.addAttribute("erro","请勿反复添加该商品！");
            }
            return "goodpage";
        }
        else
        {
            if(set==2)
            {
                Goods in=goodsService.getGoodsByNumber(number);
                if(in.getUdstatus()==1)
                {
                    List<Goods> list=new ArrayList<Goods>();
                    list.add(in);
                    model.addAttribute("pageination0",list);
                    model.addAttribute("set",1);
                    model.addAttribute("number",number);
                    System.out.println("11111111111likun");
                    return "addorders";
                }
                else
                {
                    System.out.println("2222222222likun");
                    return "redirect:/index";
                }
            }
            else
            {
                if(set==3)
                {
                    Goods in=goodsService.getGoodsByNumber(number);
                    if(in.getUdstatus()==1)
                    {
                        List<Goods> list=new ArrayList<Goods>();
                        list.add(in);
                        model.addAttribute("pageination0",list);
                        model.addAttribute("set",2);
                        model.addAttribute("number",number);
                        System.out.println("3333likun");
                        return "addorders";
                    }
                    else
                    {
                        System.out.println("4444likun");
                        return "redirect:/shoppingcar?page="+page+"&erro="+ URLEncoder.encode("有商品已下架，先删除该商品在购买！");
                    }
                }
                List<Cars> list=carsService.getCarsNumberById(users.getId());
                if(list.size()==0)
                {
                    return "redirect:/shoppingcar?erro="+URLEncoder.encode("购物车为空，请先添加购物车！");
                }
                List<Goods> l=new ArrayList<Goods>();
                Goods in=null;
                int t=0;
                for(Cars c: list){
                   in=goodsService.getGoodsByNumber(c.getGood());
                   l.add(in);
                   if(in.getUdstatus()!=1)
                   {
                       t=1;
                       break;
                   }
                }
                if(t==1)
                {
                    return "redirect:/shoppingcar?page="+page+"&erro="+URLEncoder.encode("有商品已下架，先删除该商品在购买！");
                }
                else
                {
                    model.addAttribute("pageination0",l);
                    model.addAttribute("set",3);
                    System.out.println("555likun");
                    return "addorders";
                }
            }
        }
    }
}

    /*@RequestMapping(value = "/addshoppingcar",method = RequestMethod.GET)
    public String addshoppingCar(HttpServletRequest request,
                              Model model,
                              @RequestParam(name="set",defaultValue = "1") Integer set,//标记来自不同页面的请求
                              @RequestParam(name="number",defaultValue = "-1") Integer number//各自页面中当前操作的商品number
                              ){
        System.out.println(set+"哈哈1");
        Object o=request.getSession().getAttribute("users");
        if(o==null)
        {
            return "redirect:/login?number="+number;
        }
        if(set==1)
        {
            ShoopingCar t=common(o,page,size,set);
            model.addAttribute("carInfor",t);
            return "shoppingcar";
        }
        if(set==5)
        {
            System.out.println(((Users)o).getId()+"All123");
            carsService.updateAll(((Users)o).getId());
            ShoopingCar t=common(o,page,size,set);
            model.addAttribute("carInfor",t);
            return "shoppingcar";
        }
        if(set==3)
        {
            carsService.updateByNumber(number);
            System.out.println(((Users)o).getId()+"number123");
            ShoopingCar t=common(o,page,size,set);
            model.addAttribute("carInfor",t);
            return "shoppingcar";
        }
        return "shoppingcar";
    }
*/