package com.atguigu.springboot04.bean;

import java.util.List;

public class Kinds {
    private Integer number;//编号
    private String name;//种类名
    private Integer type;//该种类的父种类编号
    private List<String> second;//二级种类名

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<String> getSecond() {
        return second;
    }

    public void setSecond(List<String> second) {
        this.second = second;
    }
}
