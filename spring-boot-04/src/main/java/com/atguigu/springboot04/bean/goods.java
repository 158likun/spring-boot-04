package com.atguigu.springboot04.bean;

public class goods {
    private Integer number;
    private String id;
    private String kind;
    private Double price;
    private String name;
    private String color;
    private String picture;
    private Integer udstatus;
    private Integer deletstatus;
    private String description;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getUdstatus() {
        return udstatus;
    }

    public void setUdstatus(Integer udstatus) {
        this.udstatus = udstatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDeletstatus() {
        return deletstatus;
    }

    public void setDeletstatus(Integer deletstatus) {
        this.deletstatus = deletstatus;
    }
}
