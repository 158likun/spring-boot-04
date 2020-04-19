package com.atguigu.springboot04.controller;

import com.atguigu.springboot04.bean.Goods;
import com.atguigu.springboot04.bean.Kinds;
import com.atguigu.springboot04.bean.Users;
import com.atguigu.springboot04.dto.Page;
import com.atguigu.springboot04.dto.PutAway;
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
import java.util.List;
import java.util.Random;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    //@ApiOperation(value="MyBatis_Demo", notes="MyBatis实现数据库访问demo")
    @RequestMapping(value = "/goods",method = RequestMethod.GET)
    public String redirect(HttpServletRequest request,
                           Model model)
    {
        Users users=(Users)request.getSession().getAttribute("users");
        if(users==null)
        {
            return "redirect:/login";
        }
            List<String> list=goodsService.getSecondKinds();
            model.addAttribute("secondkinds",list);
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
   /* public int getFileName(){
        int t=goodsService.getCount();
        System.out.println(t);
        return t;
    }*/
  /*  @RequestMapping(value = "/uppage",method = RequestMethod.GET)
    public String upRedirect(){
        return "up";
    }*/
  //与商品管理有关的请求
  @RequestMapping(value = "/upgood",method = RequestMethod.GET)
  public String upGoods(HttpServletRequest request,
                        Model model,
                        @RequestParam(name="page",defaultValue = "1") Integer page,//但前要访问的页面
                        @RequestParam(name="set",defaultValue = "1") Integer set,//标记来自不同页面的请求
                        @RequestParam(name="number",defaultValue = "-1") Integer number,//各自页面中当前操作的商品number
                        @RequestParam(name="size",defaultValue = "2") Integer size){//每页所包含的数据数目
      Object o=request.getSession().getAttribute("users");
      if(o==null)
      {
          return "upgoods";
      }
      Goods goods=null;
      if(set==1)
      {
          if(number!=-1)
          {
              //上架页面点击下架
              goods=goodsService.getGoodsByNumber(number);//痛过number得到商品,不管udstatus的值
              System.out.println(number+"    lk");
              if(goods.getUdstatus()==1)
              {
                  goodsService.updateGoodsof(set,number);
              }
              else
              {
                  model.addAttribute("erro","该商品已下架或已出售！");
              }
          }
          Page t=common(o,page,size,set);
          model.addAttribute("pageination",t);
          return "upgoods";
      }
      if(set==0)
      {
          if(number!=-1)
          {
              //下架页面点击删除
              goods=goodsService.getGoodsByNumber(number);//痛过number得到商品,不管udstatus的值
              if(goods.getUdstatus()==0)
              {
                  goodsService.updateGoodsof(set,number);
              }
              else
              {
                  model.addAttribute("erro","该商品已上架或已出售！");
              }
          }
          Page t=common(o,page,size,set);
          model.addAttribute("pageination0",t);
          return "downgoods";
      }
     /* if(set==2)
      {
          goodsService.updateGoodsof(set,number);
          Page t=common(o,page,size,set);
          model.addAttribute("pageination2",t);
          return "upgoods";
      }*/
      if(set==3)
      {
          //点击下架商品页面的上架链接
          goods=goodsService.getGoodsByNumber(number);//痛过number得到商品,不管udstatus的值
          if(goods.getUdstatus()==0)
          {
              Goods g=goodsService.getGoodsByNumber(number);
              PutAway putaway=new PutAway();
              putaway.setGood(g);
              putaway.setPage(page);
              model.addAttribute("putawaypage",page);
              model.addAttribute("putawaynumber",number);
              model.addAttribute("putaway",putaway);
              model.addAttribute("secondkinds",goodsService.getSecondKinds());
              return "putaway";
          }
          else
          {
              model.addAttribute("erro","该商品已上架或已出售！");
              Page t=common(o,page,size,0);
              model.addAttribute("pageination0",t);
              return "downgoods";
          }
      }
      return "upgoods";
  }
  public Page common(Object o,Integer page,Integer size,Integer set){
      Users users=(Users)o;
      String id=users.getId();
      int s=goodsService.getCount(id,set);
      Page t=new Page();
      t.setPageInation(s,page,size);
      List<Goods> list=null;
      if(t.getPage()>=1)
      {
          list=goodsService.getGoodsById(id,((t.getPage()-1)*size),size,set);
      }
//      for(Integer h:t.getPages()){
//          System.out.println(h+" ");
//      }
      t.setGoods(list);
      return t;
  }
  //接受添加商品页面的form表单的地址upgoods
    @RequestMapping(value = "/upgoods",method = RequestMethod.POST)
    public String tijiao( @RequestParam(name="file")MultipartFile file,//传MultipartFile file 的form表单method必须为POST
                         HttpServletRequest request,
                          Model model) {
        Goods goods = new Goods();
        String filename=file.getOriginalFilename();
        int name= getFileName("images/",filename.substring(filename.lastIndexOf(".")));
        System.out.println(filename+"0");
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File("D:\\spring-boot-04\\src\\main\\resources\\static\\images\\"+name+filename.substring(filename.lastIndexOf(".")))));//保存图片到目录下
                out.write(file.getBytes());
                out.flush();
                out.close();
                String s="images/"+name+filename.substring(filename.lastIndexOf("."));
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
               /* model.addAttribute(goods);*/
                /*model.addAttribute()*/
                return "goods";
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
        } else {
            return "上传失败，因为文件是空的.";
        }
    }
    //接受putaway页面的form表单的请求
    @RequestMapping(value = "/putaway",method = RequestMethod.POST)
    public String putaway( @RequestParam(name="file")MultipartFile file,//传MultipartFile file 的form表单method必须为POST
                           @RequestParam(name="putawaypage") Integer putawaypage,
                           @RequestParam(name="putawaynumber") Integer putawaynumber,
                          HttpServletRequest request,
                          Model model) {
        Goods goods = goodsService.getGoodsByNumber(putawaynumber);
        if(goods.getUdstatus()==2)
        {
            //情况用户两地登录，其中一人已经上架该商品，并被人购买了
            return "redirect:/index";
        }
        String filename=file.getOriginalFilename();
        System.out.println(filename);
        if("".equals(filename))
        {
        //如果上架下架商品时不选图片要处理
            //增加商品
            goods.setId(((Users)request.getSession().getAttribute("users")).getId());
            goods.setKind(request.getParameter("kind"));
            goods.setName(request.getParameter("name"));
            goods.setPrice(new Double(request.getParameter("price")));
            goods.setColor(request.getParameter("color"));
            goods.setDescription(request.getParameter("description"));
            goods.setNumber(putawaynumber);
            goodsService.updateOrigin(goods);
            return "redirect:/upgood?page="+putawaypage+"&set=0";
        }
        int name= getFileName("images/",filename.substring(filename.lastIndexOf(".")));
        System.out.println(filename+"0");
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File("D:\\spring-boot-04\\src\\main\\resources\\static\\images\\"+name+filename.substring(filename.lastIndexOf(".")))));//保存图片到目录下
                out.write(file.getBytes());
                out.flush();
                out.close();
                String s="images/"+name+filename.substring(filename.lastIndexOf("."));
                System.out.println(s);
                //增加商品
                goods.setId(((Users)request.getSession().getAttribute("users")).getId());
                goods.setKind(request.getParameter("kind"));
                goods.setName(request.getParameter("name"));
                goods.setPrice(new Double(request.getParameter("price")));
                goods.setColor(request.getParameter("color"));
                goods.setPicture(s);
                goods.setDescription(request.getParameter("description"));
                goods.setNumber(putawaynumber);
                goodsService.updateGoods(goods);
                return "redirect:/upgood?page="+putawaypage+"&set=0";
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
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
