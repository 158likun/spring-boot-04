package com.atguigu.springboot04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

//这个类的所有方法返回的数据直接写给浏览器，（如果是对象转为json数据）
/*@ResponseBody//只能返回String,返回url只能写controller
@Controller*/
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    // RESTAPI的方式
}
