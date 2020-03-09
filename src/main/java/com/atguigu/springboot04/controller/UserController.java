package com.atguigu.springboot04.controller;



import com.atguigu.springboot04.bean.User;

import com.atguigu.springboot04.service.UserService;

import com.atguigu.springboot04.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //@ApiOperation(value="MyBatis_Demo", notes="MyBatis实现数据库访问demo")
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public User getUser()
    {
        User t=userService.getUser();
        return t;
    }
}