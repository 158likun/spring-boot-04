package com.atguigu.springboot04.bean;

import java.util.Date;

public class Orders {
    //orders表中status表示买方订单状态0表示删除1表示待付款，2表示已付款；sellstatus表示卖方订单状态0表示删除1表示待付款，2表示已付款
    private Integer number;
    private String idb;
    private String ids;
    private Double price;
    private Integer numberg;
    private Integer status;//买方状态
    private Integer sellstatus;//卖方状态
    /*private Date time;//日期*/
    private String date;//日期转换格式
    private String name;
    private String bz;
    private String description;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getIdb() {
        return idb;
    }

    public void setIdb(String idb) {
        this.idb = idb;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumberg() {
        return numberg;
    }

    public void setNumberg(Integer numberg) {
        this.numberg = numberg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSellstatus() {
        return sellstatus;
    }

    public void setSellstatus(Integer sellstatus) {
        this.sellstatus = sellstatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   /* public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }*/

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
