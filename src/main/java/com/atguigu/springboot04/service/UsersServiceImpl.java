package com.atguigu.springboot04.service;




import com.atguigu.springboot04.bean.Users;
import com.atguigu.springboot04.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersServiceImpl implements UsersService {
    //连接到UserMapper Bean
    @Autowired//(required = false)
    private UsersMapper usersMapper;
    @Override
    public List<Users> getUsers()
    {
        return usersMapper.findAll();
    }
    @Override
    public Users getUsers(String id)
    {

        return usersMapper.findById(id);
    }
    @Override
    public Map<String,Object> findUsers(String id,String password)
    {
        /*Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",id);
        map.("password",password);*/
        Users users=usersMapper.findById(id);
        Map<String,Object> map=new HashMap<String,Object>();
        if(users==null)
        {
            map.put("int",0);
            map.put("object",null);
            return map;
        }
        if(users.getPassword().equals(password)==false)
        {
            map.put("int",1);
            map.put("object",null);
            return map;
        }
        else
        {
            map.put("int",2);
            map.put("object",users);
            return map;
        }
    }
    @Override
    public Boolean insertUsers(Users entity)
    {
        return usersMapper.insert(entity)>0;
    }

    @Override
    public Boolean updateUsers(Users entity)
    {
        return usersMapper.update(entity)>0;
    }
}
