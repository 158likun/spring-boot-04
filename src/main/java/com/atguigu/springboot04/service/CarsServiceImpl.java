package com.atguigu.springboot04.service;
import com.atguigu.springboot04.bean.Cars;
import com.atguigu.springboot04.mapper.CarsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarsServiceImpl implements CarsService {
    @Autowired
    private CarsMapper carsMapper;
    public List<Cars> getCars(String id,int l,int r){
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("id",id);
        map.put("l",l);
        map.put("r",r);
        List<Cars> s=null;
        s=carsMapper.getCars(map);
        return s;
    }

    public int update(int number){
        int s=carsMapper.update(number);
        return s;
    }
    public int getCount(String id){
        return carsMapper.getCount(id);
    }
    public int updateAll(String id){
        return carsMapper.updateAll(id);
    }
    public int updateByNumber(int number){
        return carsMapper.updateByNumber(number);
    }
}
