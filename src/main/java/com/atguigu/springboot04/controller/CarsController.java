package com.atguigu.springboot04.controller;

import com.atguigu.springboot04.bean.Cars;
import com.atguigu.springboot04.bean.Goods;
import com.atguigu.springboot04.bean.Users;
import com.atguigu.springboot04.dto.CarsInformation;
import com.atguigu.springboot04.dto.Page;
import com.atguigu.springboot04.dto.PutAway;
import com.atguigu.springboot04.dto.ShoopingCar;
import com.atguigu.springboot04.service.CarsService;
import com.atguigu.springboot04.service.GoodsService;
import com.atguigu.springboot04.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@Cacheable
@Controller
public class CarsController {
    @Autowired
    private CarsService carsService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UsersService usersService;
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
            return "shoppingcar";
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