package com.atguigu.springboot04.controller;

import com.atguigu.springboot04.bean.Users;
import com.atguigu.springboot04.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;

    //@ApiOperation(value="MyBatis_Demo", notes="MyBatis实现数据库访问demo")
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public Users getUser()
    {
        Users t=usersService.getUsers();
        return t;
    }
}
