package com.atguigu.springboot04.controller;

import com.atguigu.springboot04.bean.Goods;
import com.atguigu.springboot04.bean.Kinds;
import com.atguigu.springboot04.bean.Users;
import com.atguigu.springboot04.dto.Page;
import com.atguigu.springboot04.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//这个类的所有方法返回的数据直接写给浏览器，（如果是对象转为json数据）
/*@ResponseBody//只能返回String,返回url只能写controller
@Controller*/
@Controller
public class IndexController {
    @Autowired
    private GoodsService goodsService;
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name="page",defaultValue = "1") Integer page,//但前要访问的页面
                        @RequestParam(name="set",defaultValue = "1") Integer set,//标记来自不同页面的请求
                        @RequestParam(name="number",defaultValue = "-1") Integer number,//各自页面中当前操作的商品number
                        @RequestParam(name="size",defaultValue = "8") Integer size){//每页所包含的数据数目
        Page t=common(page,size,set);
        List<Kinds> l=goodsService.getKinds();
        model.addAttribute("indexpage",t);
        model.addAttribute("indexkind",l);
        System.out.println(l+" 1213");
        return "index";
    }
    public Page common(Integer page,Integer size,Integer set){
        int s=goodsService.getCount(set);
        Page t=new Page();
        t.setPageInation(s,page,size);
        List<Goods> list=null;
        if(t.getPage()>=1)
        {
            list=goodsService.getGoodsById(((t.getPage()-1)*size),size,set);
        }
        t.setGoods(list);
        return t;
    }
    // RESTAPI的方式
}
