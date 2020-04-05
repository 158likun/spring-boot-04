package com.atguigu.springboot04.dto;

import com.atguigu.springboot04.bean.Cars;
import com.atguigu.springboot04.bean.Goods;
import com.atguigu.springboot04.bean.Users;
//购物车页面信息表
public class CarsInformation {
    private Goods good;
    private Cars car;
    private Users users;

    public Goods getGood() {
        return good;
    }

    public void setGood(Goods good) {
        this.good = good;
    }

    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
