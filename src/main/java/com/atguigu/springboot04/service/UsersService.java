package com.atguigu.springboot04.service;

import com.atguigu.springboot04.bean.Users;

import java.util.List;
import java.util.Map;

public interface UsersService {
    public Users getUsers(String id);
    public Map<String,Object> findUsers(String id, String password);
    public List<Users> getUsers();
    public Boolean insertUsers(Users entity);
    public Boolean updateUsers(Users entity);
}
