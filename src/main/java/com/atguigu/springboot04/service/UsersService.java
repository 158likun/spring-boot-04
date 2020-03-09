package com.atguigu.springboot04.service;

import com.atguigu.springboot04.bean.Users;

public interface UsersService {
    public Users getUsers(String id);
    public Users getUsers();
    public Boolean insertUsers(Users entity);
    public Boolean updateUsers(Users entity);
}
