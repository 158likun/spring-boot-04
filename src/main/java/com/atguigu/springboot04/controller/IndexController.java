package com.atguigu.springboot04.controller;

import com.atguigu.springboot04.bean.Goods;
import com.atguigu.springboot04.bean.Kinds;
import com.atguigu.springboot04.bean.Users;
import com.atguigu.springboot04.bean.Words;
import com.atguigu.springboot04.dto.Page;
import com.atguigu.springboot04.service.GoodsService;
import com.atguigu.springboot04.service.WordsService;
import org.apache.ibatis.annotations.Param;
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
    @Autowired
    private WordsService wordsServicec;
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name="page",defaultValue = "1") Integer page,//但前要访问的页面
                        @RequestParam(name="set",defaultValue = "1") Integer set,//标记来自不同页面的请求
                        @RequestParam(name="number",defaultValue = "-1") Integer number,//各自页面中当前操作的商品number
                        @RequestParam(name="size",defaultValue = "8") Integer size,//每页所包含的数据数目
                        @RequestParam(name="kind",defaultValue = "-1") String kind
                        ){
        Page t=common(page,size,set,kind);
        List<Kinds> l=goodsService.getKinds();
        model.addAttribute("indexpage",t);
        model.addAttribute("indexkind",l);
        if(kind.equals("-1"))
        {
            model.addAttribute("kind","-1");
        }
        else
        {
            model.addAttribute("kind",kind);
        }
        System.out.println(l+" 1213");
        return "index";
    }
    public Page common(Integer page,Integer size,Integer set,String kind){
        if(kind.equals("-1"))
        {
            int s=goodsService.getCountAll(set);
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
       else
        {
            //点击二级分类查询商品
            int s=goodsService.getCountByKind(kind);
            Page t=new Page();
            t.setPageInation(s,page,size);
            List<Goods> list=null;
            if(t.getPage()>=1)
            {
                list=goodsService.getGoodsByKinds(kind,((t.getPage()-1)*size),size);
            }
            t.setGoods(list);
            return t;
        }
    }
    // RESTAPI的方式
    //首页进入商品详情页面
    @RequestMapping(value = "/goodpage",method = RequestMethod.GET)
    public String goodPage(Model model,
                            @RequestParam(name="number") Integer number,
                           @RequestParam(name="set",defaultValue = "0") Integer set,//set=1表示从上架商品页面点击商品编号进入商品详情页面
                           @RequestParam(name="page",defaultValue = "-1") Integer page
                           ){
        Goods g=goodsService.getGoodsByNumber(number);
        if(set==1&&(g.getUdstatus()!=1||g.getDeletstatus()==1))
        {
            //表示从上架商品页面点击商品编号进入商品详情页面时该商品已出售或已下架
            model.addAttribute("erro","该商品已下架或已出售！");
            return "redirect:/upgood?page="+page;
        }
        //长期处于商品详情页面，但该商品已下架时，点击任何事件
        if(g.getUdstatus()!=1||g.getDeletstatus()==1)
        {
            return "redirect:/index";
        }
        model.addAttribute("goodpage",g);
        model.addAttribute("all",0);
        //留言区
        List<Words> words=wordsServicec.getWords(number);
        for(Words c: words)
        {
            System.out.println(c.getId()+" "+c.getDate());
        }
        model.addAttribute("comment",words);
        //推荐区，推荐商品
        List<Goods> recommends=goodsService.getRecommends(g);
        model.addAttribute("recommends",recommends);
        return "goodpage";
    }
    @RequestMapping(value = "/ups",method = RequestMethod.GET)
    public String ups(Model model
    ){
        model.addAttribute("sure","index");
        return "up";
    }
   /* @RequestMapping(value = "/kind",method = RequestMethod.GET)
    public String kind(Model model
    ){
        model.addAttribute("sure","index");
        return "up";
    }*/
    //搜索按钮查询
    @RequestMapping(value = "/check",method = RequestMethod.GET)
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name="page",defaultValue = "1") Integer page,//当前要访问的页面
                        @RequestParam(name="size",defaultValue = "10") Integer size,//每页所包含的数据数目
                        @RequestParam(name="check") String check
    ){
        /*if(check.trim().equals(""))
        {
            model.addAttribute("erro","关键字不能为空！");

        }*/
        Page t=common(page,size,check);
        model.addAttribute("indexpage",t);
        model.addAttribute("ckeck",check);
        return "checkpage";
    }
    public Page common(Integer page,Integer size,String check) {
        int s = goodsService.getCountByCheck(check);
        Page t = new Page();
        t.setPageInation(s, page, size);
        List<Goods> list = null;
        if (t.getPage() >= 1) {
            list = goodsService.getGoodsByCheck( check,((t.getPage() - 1) * size), size);
        }
        t.setGoods(list);
        return t;
    }
}
