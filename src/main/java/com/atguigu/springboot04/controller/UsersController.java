package com.atguigu.springboot04.controller;

import com.atguigu.springboot04.bean.Users;
import com.atguigu.springboot04.service.UsersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;

    //@ApiOperation(value="MyBatis_Demo", notes="MyBatis实现数据库访问demo")
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login()
    {
        //Users t=usersService.findUsers("B20160304424","123456");
        return "login";
    }
    @RequestMapping(value = "/logintrue",method = RequestMethod.POST)
    public String getUsers(@RequestParam(name="id") String id, @RequestParam(name="password") String password, HttpServletRequest request, Model model)
    {
        //当参数太多直接Users users然后users.getName();
        //name为html页面name属性值
        //Model 用于传递信息到html页面
        //model.setAttribute();
        //${};
        if("".equals(id)||"".equals(password))
        {
            model.addAttribute("erro","账号或密码不能为空！");
            return "login";
        }
        Map<String,Object> map=usersService.findUsers(id,password);
        int t=(int)map.get("int");
        if(t==0)
        {
            model.addAttribute("erro","账号不存在！");
            return "login";
        }
        else
        {
            if(t==1)
            {
                model.addAttribute("erro","密码不正确！");
                return "login";
            }
            else
            {
                Users users=(Users)map.get("object");
                request.getSession().setAttribute("users",users);
                return "redirect:/index";
            }
        }

    }
    @RequestMapping(value = "/loginfalse",method = RequestMethod.GET)
    public String deletLogin(HttpServletRequest requst){
        requst.getSession().removeAttribute("users");
        return "redirect:/index";
    }
    @RequestMapping(value = "/register",method = RequestMethod.GET)

    public String register()
    {
       /* Users users=new Users();
        users.setId("");
        users.setName("1");
        users.setSex("");
        users.setPassword("");
        users.setDescription("");
        boolean t=usersService.insertUsers(users);*/
        return "register";
    }
    @RequestMapping(value = "/registertrue",method = RequestMethod.POST)

    public String insertUsers(Users users,Model model)
    {
        System.out.println(users.getId()+"\n"+users.getPassword()+"\n"+users.getSex()+"\n"+users.getDescription());
        if("".equals(users.getDescription()))
        {
            System.out.println(123);
        }
       /* Users users=new Users();
        users.setId("");
        users.setName("1");
        users.setSex("");
        users.setPassword("");
        users.setDescription("");
        boolean t=usersService.insertUsers(users);*/
       if("".equals(users.getId())||"".equals(users.getPassword())||"".equals(users.getName())||"".equals(users.getSex()))
       {
           model.addAttribute("erro","除描述外不能为空！");
           return  "register";
       }
        Users user=usersService.getUsers(users.getId());
       if(user!=null)
       {
           model.addAttribute("erro","该用户已存在！");
           return "register";
       }
       else
       {
           if("".equals(users.getDescription()))
           {
               users.setDescription(null);
           }
           usersService.insertUsers(users);
           return "redirect:/login";
       }


    }
}
