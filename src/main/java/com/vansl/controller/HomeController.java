package com.vansl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: vansl
 * @create: 18-3-17 下午11:58
 */
@Controller
public class HomeController {
    //添加日志器
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    //action映射
    @GetMapping("/")
    public  String index(HttpServletRequest request){
        //输出日志文件
        logger.info("Build Success");
        //返回index.jsp
        return "index";
    }

    @GetMapping("/files")
    public  String listFiles(HttpServletRequest request){
        return "files";
    }

    @RequestMapping("getTime")
    @ResponseBody
    public String getTime(@RequestParam String format, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("UTF-8");
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat (format);
        return df.format(date);
    }

}