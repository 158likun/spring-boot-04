package com.atguigu.springboot04.service;

import com.atguigu.springboot04.bean.User;
import org.springframework.stereotype.Service;


public interface UserService {
    public User getUser(String id);
    public User getUser();
    public Boolean insertUser(User entity);
    public Boolean updateUser(User entity);
}
