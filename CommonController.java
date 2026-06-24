package com.warehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 跳转控制器
 */
@Controller
public class CommonController {

    /**
     * 01-访问 "/"地址，跳转到 login.html模板引擎上
     * @return
     */
    @GetMapping("/")
    public String toLoginUI(){
        return "login";
    }

    /**
     * 03-访问 "/index"地址，跳转到 index.html模板引擎上
     * @return
     */
    @GetMapping("/index")
    public String toIndex(){
        return "index";
    }


    /**
     * 04-访问 "/index"地址，跳转到 index.html模板引擎上
     * @return
     */
    @GetMapping("/calculator.html")
    public String tocalculator(){
        return "calculator";
    }

    /**
     * 05-访问 "/calendar"地址，跳转到 calendar.html模板引擎上
     * @return
     */
    @GetMapping("/calendar.html")
    public String tocalendar(){
        return "calendar";
    }

    /**
     * 05-访问 "/clock"地址，跳转到 clock.html模板引擎上
     * @return
     */
    @GetMapping("/clock.html")
    public String toclock(){
        return "clock";
    }

    /**
     * 05-访问 "/map"地址，跳转到 map.html模板引擎上
     * @return
     */
    @GetMapping("/map.html")
    public String tomap(){
        return "map";
    }


    /**
     * 06-访问 "/loginlog.html"地址，跳转到 loginlog.html模板引擎上
     * @return
     */
    @GetMapping("/loginlog.html")
    public String tomloginlog(){
        return "loginlog";
    }

    /**
     * 06-访问 "/register.html"地址，跳转到 register.html模板引擎上
     * @return
     */
    @GetMapping("/register.html")
    public String toregister(){
        return "register";
    }
}

