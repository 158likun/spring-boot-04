package com.atguigu.springboot04.controller;

import com.atguigu.springboot04.bean.Users;
import com.atguigu.springboot04.service.CarsService;
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
    @Autowired
    private CarsService carsService;
    //@ApiOperation(value="MyBatis_Demo", notes="MyBatis实现数据库访问demo")
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model,
                         @RequestParam(name="number",defaultValue = "0")int number){
       model.addAttribute("number",number);
       model.addAttribute("background","images/index.jpg");
        //Users t=usersService.findUsers("B20160304424","123456");
        return "login";
    }
    @RequestMapping(value = "/logintrue",method = RequestMethod.POST)
    public String getUsers(@RequestParam(name="id") String id,
                           @RequestParam(name="password") String password,
                           @RequestParam(name="number",defaultValue = "0") int number,
                           HttpServletRequest request, Model model)
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
                System.out.println("users.getDescription():"+users.getDescription());
                if(number==0)
                {
                    return "redirect:/index";
                }
                else
                {
                    return "redirect:/goodpage?number="+number;
                }
            }
        }

    }
    //注销
    @RequestMapping(value = "/loginfalse",method = RequestMethod.GET)
    public String deletLogin(HttpServletRequest requst){
        requst.getSession().removeAttribute("users");
        return "redirect:/index";
    }
    //首页注册按钮跳转
    @RequestMapping(value = "/register",method = RequestMethod.GET)

    public String register(Model model)
    {
       /* Users users=new Users();
        users.setId("");
        users.setName("1");
        users.setSex("");
        users.setPassword("");
        users.setDescription("");
        boolean t=usersService.insertUsers(users);*/
        model.addAttribute("background","images/index.jpg");
        return "register";
    }
    //register页面中form表单跳转
      @RequestMapping(value = "/registertrue",method = RequestMethod.POST)

    public String insertUsers(Users users,Model model)
    {
        model.addAttribute("background","images/index.jpg");
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
    //首页个人管理按钮跳转
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String checkUsers(HttpServletRequest request){
        Users users=(Users)request.getSession().getAttribute("users");
        if(users==null)
        {
            return "redirect:/login";
        }
        return "users";
    }
    //个人管理页面左侧下来导航栏修改信息超链接地址
    @RequestMapping(value = "/userschange",method = RequestMethod.GET)
    public String changeUsres(HttpServletRequest requst,Model model){
        return "userschange";
    }
    //个人管理的userschange界面的确认修改按钮的控制器
    @RequestMapping(value = "/userschangetrue",method = RequestMethod.GET)
    public String changeUsresTrue(HttpServletRequest requst,Model model){
        if("".equals(requst.getParameter("name")))
        {
            model.addAttribute("userschangeerro","用户名称不能为空！");
            return "userschange";
        }
        if("".equals(requst.getParameter("password"))||"".equals(requst.getParameter("newpassword")))
        {
            model.addAttribute("userschangeerro","原密码或新密码不能为空或！");
            return "userschange";
        }
        else
        {
            System.out.println(requst.getParameter("id"));
            Users users=usersService.getUsers(requst.getParameter("id"));
            if(!users.getPassword().equals(requst.getParameter("password")))
            {
                model.addAttribute("userschangeerro","原密码错误！");
                return "userschange";
            }
            else
            {
                //表单users
                Users b=new Users();
                b.setId(requst.getParameter("id"));
                b.setName(requst.getParameter("name"));
                b.setPassword(requst.getParameter("newpassword"));
                b.setSex(requst.getParameter("sex"));
                b.setDescription(requst.getParameter("description"));
                usersService.updateUsers(b);
                requst.getSession().setAttribute("users",b);
                return "users";
            }
        }

    }
}
