package com.atguigu.springboot04.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTrans {
    public static String getSystemDate(){
        SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return d.format(new Date());
    }
}
