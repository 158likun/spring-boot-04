package com.atguigu.springboot04.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//这个类的所有方法返回的数据直接写给浏览器，（如果是对象转为json数据）
/*@ResponseBody
@Controller*/
@Controller
public class HelloController {


    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "hello";
    }

    // RESTAPI的方式
}
