package com.atguigu.springboot04.service;

import com.atguigu.springboot04.bean.Users;

import java.util.List;

public interface UsersService {
    public Users getUsers(String id);
    public List<Users> getUsers();
    public Boolean insertUsers(Users entity);
    public Boolean updateUsers(Users entity);
}
