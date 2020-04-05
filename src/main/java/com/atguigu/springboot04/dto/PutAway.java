package com.atguigu.springboot04.dto;

import com.atguigu.springboot04.bean.Goods;

//下架页面上架时属性类
public class PutAway {
    private Integer page;//该上架商品所处下架商品页面的页面数
    private Integer set=0;//跳转到下架页面的操作数
    private Goods good;//该上架商品信息
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Goods getGood() {
        return good;
    }

    public void setGood(Goods good) {
        this.good = good;
    }
}
