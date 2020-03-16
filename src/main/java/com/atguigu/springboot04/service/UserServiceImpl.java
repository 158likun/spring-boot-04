package com.atguigu.springboot04.service;

import com.atguigu.springboot04.bean.User;
import com.atguigu.springboot04.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service //声明成一个spring bean
public class UserServiceImpl implements UserService {

    //连接到UserMapper Bean
    @Autowired//(required = false)
    private UserMapper userMapper;
    @Override
    public List<User> getUser()
    {
        return userMapper.findAll();
    }
    @Override
    public User getUser(String id)
    {

        return userMapper.findById(id);
    }

    @Override
    public Boolean insertUser(User entity)
    {
        return userMapper.insert(entity)>0;
    }

    @Override
    public Boolean updateUser(User entity)
    {
        return userMapper.update(entity.getPassword(),entity.getId())>0;
    }
}
