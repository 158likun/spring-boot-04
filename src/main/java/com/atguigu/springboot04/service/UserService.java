package com.atguigu.springboot04.service;

import com.atguigu.springboot04.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    public User getUser(String id);
    /*public List<User> getUser();*/
    public Boolean insertUser(User entity);
    public Boolean updateUser(User entity);
}
