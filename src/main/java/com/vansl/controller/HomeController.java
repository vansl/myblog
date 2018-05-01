package com.vansl.controller;

import com.alibaba.fastjson.JSON;
import com.vansl.service.BlogService;
import com.vansl.service.BlogTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: vansl
 * @create: 18-3-17 下午11:58
 */
@Controller
public class HomeController {

    @Autowired
    BlogService blogService;

    @Autowired
    BlogTypeService blogTypeService;

    //添加日志器
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    //action映射
    @GetMapping("/")
    public  String select(HttpServletRequest request){
        //输出日志文件
        logger.info("Build Success");
        //返回select.jsp
        return "select";
    }

    @GetMapping("/index")
    public  String index(HttpServletRequest request){
        request.setAttribute("test","welcome");
        request.setAttribute("articleList",blogService.selectAll(1,true,1,Integer.MAX_VALUE).getData());
        request.setAttribute("typeData", JSON.toJSONString(blogTypeService.selectAll(1)));
        //返回index.jsp
        return "index";
    }

}