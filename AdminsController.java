package com.warehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.warehouse.common.DataResults;
import com.warehouse.common.TimeUtils;
import com.warehouse.entity.Admins;
import com.warehouse.entity.Loginlog;
import com.warehouse.service.AdminsService;
import com.warehouse.service.LoginlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 控制层
 */
@Controller
@RequestMapping("/admins")
public class AdminsController {

    @Autowired
    AdminsService adminsService;

    @Autowired
    LoginlogService loginlogService;

    /**
     * 注册
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public DataResults register(String username, String password,String nickname,String phone,String email) {
        Admins admins=new Admins();
        admins.setUsername(username);
        admins.setPassword(password);
        admins.setNickname(nickname);
        admins.setEmail(email);
        admins.setPhone(phone);
        admins.setDel(0);//删除状态
        Admins a = adminsService.getOne(new QueryWrapper<Admins>().eq("username", username));
        if(a!=null){
            return new DataResults(500, "账户已存在", null);
        }else{
            adminsService.save(admins);
            return new DataResults(200, "注册成功", null);
        }
    }

    /**
     * 01-管理员登录API
     */
    @PostMapping("/login")
    @ResponseBody
    public DataResults login(String username, String password, String usercode, HttpSession session, HttpServletRequest request){
        //获取系统验证码
        String syscode= (String) session.getAttribute("syscode");
        if(syscode.equalsIgnoreCase(usercode)){
            //根据账号和密码查询一个对象
            Admins admins = adminsService.getOne(new QueryWrapper<Admins>().eq("username", username).eq("password", password));
            if(admins!=null){
                //把用户的信息存储在session作用域中
                session.setAttribute("currentusers",admins);

                //存储用户登录日志
                Loginlog loginlog=new Loginlog();
                loginlog.setUsername(admins.getUsername());
                loginlog.setIp(request.getRemoteAddr());
                loginlog.setLogintime(TimeUtils.now());
                loginlogService.save(loginlog);


                return new DataResults(200,"登录成功!",null);
            }else{
                return new DataResults(500,"账号或密码错误！",null);
            }
        }else{
            return new DataResults(500,"验证码错误！",null);
        }
    }

    /**
     * 02-用户退出
     * @return
     */
    @GetMapping("/logout")
    @ResponseBody
    public DataResults logout(HttpSession session){
        //直接让session失效
        session.invalidate();
        return new DataResults(200,"注销成功",null);
    }


}
