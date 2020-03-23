package com.atguigu.springboot04.controller;

import com.atguigu.springboot04.bean.Goods;
import com.atguigu.springboot04.bean.Users;
import com.atguigu.springboot04.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Random;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    //@ApiOperation(value="MyBatis_Demo", notes="MyBatis实现数据库访问demo")
    @RequestMapping(value = "/goods",method = RequestMethod.GET)
    public String redirect()
    {
        //Users t=usersService.findUsers("B20160304424","123456");
        //int t=goodsService.insert(goods);
        return "goods";
    }
   /* @RequestMapping(value = "/upgoods",method = RequestMethod.GET)
    public String insert(Goods goods)
    {
        //Users t=usersService.findUsers("B20160304424","123456");
        int t=goodsService.insert(goods);
        return "index";
    }*/
    public int getFileName(){
        int t=goodsService.getCount();
        System.out.println(t);
        return t;
    }
  /*  @RequestMapping(value = "/uppage",method = RequestMethod.GET)
    public String upRedirect(){
        return "up";
    }*/
  //接受上传商品页面的form表单的地址upgoods
    @RequestMapping(value = "/upgoods",method = RequestMethod.POST)
    public String tijiao( @RequestParam(name="file")MultipartFile file,//传MultipartFile file 的form表单method必须为POST
                         HttpServletRequest request,
                          Model model) {
        Goods goods = new Goods();
        String filename=file.getOriginalFilename();
        int name= getFileName("static/images/",filename.substring(filename.lastIndexOf(".")));
        System.out.println(filename+"0");
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File("D:\\spring-boot-04\\src\\main\\resources\\static\\images\\"+name+filename.substring(filename.lastIndexOf(".")))));//保存图片到目录下
                out.write(file.getBytes());
                out.flush();
                out.close();
                String s="static/images/"+name+filename.substring(filename.lastIndexOf("."));
                System.out.println(s);
                //增加商品
                goods.setId(((Users)request.getSession().getAttribute("users")).getId());
                goods.setKind(request.getParameter("kind"));
                goods.setName(request.getParameter("name"));
                goods.setPrice(new Double(request.getParameter("price")));
                goods.setColor(request.getParameter("color"));
                goods.setPicture(s);
                goods.setDescription(request.getParameter("description"));
                goodsService.insert(goods);
                model.addAttribute("page",s);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            model.addAttribute(goods);
            return "index";
        } else {
            return "上传失败，因为文件是空的.";
        }
    }
    //随机获得图片的名字
    public int getFileName(String l,String r){
        int name=0;
        Random random=new Random();
        while(true)
        {
            name=random.nextInt(10000000);
            System.out.println(l+name+r);
            System.out.println(goodsService+" 0");
            int k=goodsService.getFileName(l+name+r);
            if(k==0)
            {
                break;
            }
        }
        System.out.println(1);
        return name;
    }

}
