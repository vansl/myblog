package com.vansl.controller;

import com.vansl.entity.LoginInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: vansl
 * @create: 18-3-24 下午12:49
 */

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(HttpServletRequest request){

        Cookie[] cookies=request.getCookies();
        if (cookies!=null){
            for (Cookie cookie:cookies) {
                if(cookie.getValue().equals("testToken")){
                    return "redirect:/admin";
                }
            }
        }
        return "login";
    }

    @PostMapping("/check")
    @ResponseBody
    public String check(@RequestBody LoginInfo info, HttpServletRequest request,HttpServletResponse response) throws Exception {
        //如果没有验证码说明绕过了前端或者session过期
        if (request.getSession().getAttribute("captchacode")==null) {}

        String result;
        if (info.getUsername().equals("admin")&&info.getPassword().equals("admin")){
            Cookie cookie=new Cookie("token","testToken");
            response.addCookie(cookie);
            result="{\"info\":\"success\"}";
        }else{
            result="{\"info\":\"error\"}";
        }
        return  result;
    }

}