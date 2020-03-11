package com.atguigu.springboot04.service;




import com.atguigu.springboot04.bean.Users;
import com.atguigu.springboot04.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    //连接到UserMapper Bean
    @Autowired//(required = false)
    private UsersMapper usersMapper;
    /*@Override
    public List<Users> getUsers()
    {
        return usersMapper.findAll();
    }*/
    @Override
    public Users getUsers(String id)
    {

        return usersMapper.findById(id);
    }

    @Override
    public Boolean insertUsers(Users entity)
    {
        return usersMapper.insert(entity)>0;
    }

    @Override
    public Boolean updateUsers(Users entity)
    {
        return usersMapper.update(entity.getName(),entity.getPassword(),entity.getSex(),entity.getId())>0;
    }
}
