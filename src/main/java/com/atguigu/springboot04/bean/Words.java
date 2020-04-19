package com.atguigu.springboot04.bean;

import java.util.Date;

public class Words {
    private Integer number;
    private Integer numberg;//商品编号
    private String id;//评论人id
    private int type;//父类编号
    private String information;
    private int top;
    private int likecount;//点赞数
    private int status;
    /*private Date time;*/
    private String date;//日期转换格式

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumberg() {
        return numberg;
    }

    public void setNumberg(Integer numberg) {
        this.numberg = numberg;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }

   /* public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
*/
    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
