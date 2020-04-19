package com.atguigu.springboot04.service;

import com.atguigu.springboot04.bean.Goods;
import com.atguigu.springboot04.bean.Kinds;
import com.atguigu.springboot04.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    public int insert(Goods goods){
        int t=goodsMapper.insert(goods);
        return t;
    }
    public int updateGoods(Goods goods){
        return goodsMapper.updateGoods(goods);
    }
    public int getCountAll(Integer set){
        int t=0;
        if(set==1)
        {
            t=goodsMapper.getCountAll();
            System.out.println(t+" index1");
        }
        return t;
    }

    public int updateOrigin(Goods goods){
        return goodsMapper.updateOrigin(goods);
    }
    public int getCount(String id,Integer set){
       Map<String,Object> map=new HashMap<String,Object>();
       map.put("id",id);
       map.put("set",set);
        int t=goodsMapper.count(map);
        System.out.println(t);
        return t;
    }
    public int getCountByKind(String kind){
        return goodsMapper.getCountByKind(kind);
    }
    public int getCountByCheck(String check){
        return goodsMapper.getCountByCheck(check);
    }
    public List<Goods> getGoodsById(Integer page,Integer size,Integer set){
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("page",page);
        map.put("size",size);
        List<Goods> goods=null;
        if(set==1)
        {
            goods=goodsMapper.getIndexGoods(map);
        }
        return goods;
    }
    public List<Goods> getGoodsById(String id,Integer page,Integer size,Integer set){
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("id",id);
        map.put("page",page);
        map.put("size",size);
        map.put("set",set);
        List<Goods> goods=goodsMapper.getGoodsById(map);
        return goods;
    }
    public List<Goods> getGoodsByKinds(String kind,Integer l,Integer r){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("kind",kind);
        map.put("l",l);
        map.put("r",r);
        return goodsMapper.getGoodsByKinds(map);
    }
    public int getFileName(String name){
        System.out.println(goodsMapper+" 1");
        String t=goodsMapper.getPicture(name);
        System.out.println(t);
        if(t==null)
        {
            return 0;
        }
        else
        {
            return  1;
        }
    }
    public int updateGoodsof(Integer set,Integer number){
        int t=0;
        if(set==1)
        {
            t=goodsMapper.updateGoodsof1(number);
        }
        if(set==0)
        {
            t=goodsMapper.updateGoodsof3(number);
        }
        return t;
    }
    public Goods getGoodsByNumber(Integer number){
        return goodsMapper.getGoodsByNumber(number);
    }
    public List<Kinds> getKinds(){
        return goodsMapper.getKinds();
    }
    public List<String> getSecondKinds(){
        return goodsMapper.getSecondKinds();
    }
    //推荐商品
    public List<Goods> getRecommends(Goods g){
        Map map=new HashMap();
        map.put("number",g.getNumber());
        map.put("kind",g.getKind());
        map.put("l",0);
        map.put("r",g.getPrice()+500);
        List<Goods> goods=goodsMapper.getRecommends(map);
        System.out.println(goods+"11111111");
        List<Goods> recommends=null;
        if(!goods.isEmpty())
        {
            if(goods.size()<=10)
            {
                recommends=goods;
            }
            else
            {

                int m=0;
                Double D=((Goods)goods.get(0)).getPrice();
                for(Goods good: goods){
                   m+=1;
                   if(m>10)
                   {
                       break;
                   }
                   recommends.add(good);
                }
            }
        }
        return recommends;
    }
    public List<Goods> getGoodsByCheck(String check,Integer l,Integer r){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("check",check);
        map.put("l",l);
        map.put("r",r);
        return goodsMapper.getGoodsByCheck(map);
    }
    public int updateByNumber(Integer number){
        return goodsMapper.updateByNumber(number);
    }
    public int updateStatusByNumber(int number){
        return goodsMapper.updateStatusByNumber(number);
    }
}
