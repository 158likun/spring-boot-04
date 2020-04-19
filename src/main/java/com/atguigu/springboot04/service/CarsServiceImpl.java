package com.atguigu.springboot04.service;
import com.atguigu.springboot04.bean.Cars;
import com.atguigu.springboot04.bean.Goods;
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
    public int updateByGood(String idb,int number){
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("idb",idb);
        map.put("number",number);
        return carsMapper.updateByGood(map);
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
    public Cars getCarsByNumber(String id,Integer number){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",id);
        map.put("number",number);
       return carsMapper.getCarsByNumber(map);
    }
    public int insert(String idb,String ids,Integer number){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("idb",idb);
        map.put("ids",ids);
        map.put("number",number);
        return carsMapper.insert(map);
    }
    public  List<Cars> getCarsNumberById(String id){
        return carsMapper.getCarsNumberById(id);
    }
}
