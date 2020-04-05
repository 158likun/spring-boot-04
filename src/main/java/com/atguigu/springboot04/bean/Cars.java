package com.atguigu.springboot04.bean;

public class Cars {
    private Integer number;
    private String idb;
    private String ids;
    private int good;//商品number
    private int status;

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


    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

