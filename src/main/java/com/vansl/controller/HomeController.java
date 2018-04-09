package com.vansl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: vansl
 * @create: 18-3-17 下午11:58
 */
@Controller
public class HomeController {
    //添加日志器
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    //action映射
    @RequestMapping("/")
    public  String index(HttpServletRequest request){
        //输出日志文件
        logger.info("Build Success");
        //返回index.jsp
        return "index";
    }

    @RequestMapping("/files")
    public  String listFiles(HttpServletRequest request){
        return "files";
    }
}