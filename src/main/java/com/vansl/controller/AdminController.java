package com.vansl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: vansl
 * @create: 18-3-26 下午12:07
 */

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if (cookies!=null){
            for (Cookie cookie:cookies) {
                if(cookie.getValue().equals("testToken")){
                    return "admin";
                }
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/blog/list")
    @ResponseBody
    public String blogLwist(HttpServletRequest request){
        /*Cookie[] cookies=request.getCookies();
        if (cookies!=null){
            for (Cookie cookie:cookies) {
                if(cookie.getValue().equals("testToken")){
                    return "admin";
                }
            }
        }*/
        return "{\"title\":[\"test\"]}";
    }
}
