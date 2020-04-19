package com.atguigu.springboot04.controller;

import com.atguigu.springboot04.bean.Goods;
import com.atguigu.springboot04.bean.Users;
import com.atguigu.springboot04.bean.Words;
import com.atguigu.springboot04.service.GoodsService;
import com.atguigu.springboot04.service.WordsService;

import com.atguigu.springboot04.utils.DateTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class WordsController {
    @Autowired
    private WordsService wordsService;
    @Autowired
    private GoodsService goodsService;
    @RequestMapping(value = "/comment",method = RequestMethod.GET)
    public String post(HttpServletRequest request,
                       Model model,
                       @RequestParam(name="set",defaultValue = "1")int set,
                       @RequestParam(name="number")int number,//商品编号
                       @RequestParam(name="numberw",defaultValue = "0")int numberw,//留言编号
                       @RequestParam(name="information",defaultValue = "")String information
                       ){
        Goods goods=goodsService.getGoodsByNumber(number);
        //长期处于商品详情页面，但该商品已下架时，点击任何事件
        if(goods.getUdstatus()!=1||goods.getDeletstatus()==1)
        {
            return "redirect:/index";
        }
        Users users=(Users)request.getSession().getAttribute("users");
        if(users==null)
        {
            return "redirect:/login?number="+number;
        }
        model.addAttribute("goodpage",goods);
        List<Goods> recommends=goodsService.getRecommends(goods);
        model.addAttribute("recommends",recommends);
        if(set==1)
        {
            if(information.trim().equals(""))
            {
                model.addAttribute("erro","留言不能为空！");
            }
            else
            {
                Words w=new Words();
                w.setId(users.getId());
                w.setNumberg(number);
                w.setDate(DateTrans.getSystemDate());
                System.out.println(information);
                w.setInformation(information);
                w.setTop(wordsService.getCount(number)+1);
                wordsService.insert(w);
            }
            model.addAttribute("all",1);
            List<Words> words=wordsService.getWords(number);
            model.addAttribute("comment",words);
            model.addAttribute("words","");
            return "goodpage";
        }
        if(set==2)
        {
            model.addAttribute("all",1);
            List<Words> words=wordsService.getWordsByNumberg(number);
            model.addAttribute("comment",words);
            model.addAttribute("words","");
            return "goodpage";
        }
        if(set==3)
        {
            model.addAttribute("all",0);
            List<Words> words=wordsService.getWords(number);
            model.addAttribute("comment",words);
            model.addAttribute("words","");
            return "goodpage";
        }
        if(set==4)
        {
            wordsService.update(numberw);
            model.addAttribute("all",1);
            List<Words> words=wordsService.getWordsByNumberg(number);
            model.addAttribute("comment",words);
            model.addAttribute("words","");
            return "goodpage";
        }
        if(set==5)
        {
            Words w=wordsService.getWord(numberw);
            List<Words> words=wordsService.getWordsByNumberg(number);
            model.addAttribute("words","@"+w.getId()+":\r\n");
            model.addAttribute("all",1);
            model.addAttribute("comment",words);
            return "goodpage";
        }
        return "index";
    }

}
