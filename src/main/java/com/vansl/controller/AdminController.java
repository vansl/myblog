package com.vansl.controller;


import com.vansl.dto.LoginInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: vansl
 * @create: 18-3-24 下午12:49
 */

@Controller
public class AdminController {

    @GetMapping("/admin")
    @RequiresUser
    public String admin(HttpServletRequest request){
        return "admin";
    }

    @GetMapping("/login")
    public String login(){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //已认证或记住用户则直接跳转到后台管理
        if (subject.isRemembered()||subject.isAuthenticated()){
            return "redirect:admin";
        }
        return "login";
    }

    //登录验证
    @PostMapping("/check")
    @ResponseBody
    public String check(@RequestBody LoginInfo loginInfo) throws Exception {
        //创建用户令牌
        UsernamePasswordToken token=new UsernamePasswordToken(loginInfo.getUserName(),loginInfo.getPassword());
        //设置记住用户，默认一天
        token.setRememberMe(true);
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        try {
            //加载自定义realm并传递令牌
            subject.login(token);
            return "success";

        //认证登录失败抛出异常
        } catch (Exception e) {
            e.printStackTrace();
            return "wrong";
        }
    }

    //登录验证
    @GetMapping("/logout")
    public ModelAndView logout() {
        ModelAndView modelAndView=new ModelAndView();
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        try {
            //注销用户
            subject.logout();
            modelAndView.setViewName("redirect:login");
        //抛出异常
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.addObject("result","wrong");
        }finally {
            return modelAndView;
        }
    }


}