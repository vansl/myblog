package com.vansl.controller;

import com.vansl.dto.TableData;
import com.vansl.entity.Blog;
import com.vansl.service.BlogService;
import com.vansl.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: vansl
 * @create: 18-4-27 下午2:25
 */

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    UserService userService;

    // 查询用户的所有文章（包括草稿）
    @GetMapping(params = {"userId","offset","limit"})
    @ResponseBody
    public TableData getAllData(Integer userId, Integer offset, Integer limit){
        //身份验证
        Subject subject = SecurityUtils.getSubject();
        String userName=(String)subject.getPrincipals().getPrimaryPrincipal();
        Integer loginId=userService.selectIdByUserName(userName);
        if(userId!=loginId){
            return null;
        }

        return  blogService.selectAll(userId,false,offset,limit);
    }

    // 查询文章内容
    @GetMapping("/{id}")
    @ResponseBody
    public String getContent(@PathVariable Integer id){
        //身份验证
        Subject subject = SecurityUtils.getSubject();
        String userName=(String)subject.getPrincipals().getPrimaryPrincipal();
        Integer loginId=userService.selectIdByUserName(userName);
        Integer userId=blogService.selectUserIdByBlogId(id);
        if(userId!=loginId){
            System.out.println(userId+" "+loginId);
            return "denied";
        }

        return  blogService.selectContentByBlogId(id);
    }

    // 添加博客
    @PostMapping
    @ResponseBody
    public String addBlog(@RequestBody Blog blog, HttpServletResponse response ){
        //身份验证
        Subject subject = SecurityUtils.getSubject();
        String userName=(String)subject.getPrincipals().getPrimaryPrincipal();
        Integer loginId=userService.selectIdByUserName(userName);
        blog.setUserId(loginId);

        Integer result=blogService.insertBlog(blog);
        //操作失败返回则错误信息
        if (result==-1){
            response.setStatus(400);
            return "error";
        }else{
            return "ok";
        }
    }

    // 更新博客字段
    @PutMapping("/{id}")
    @ResponseBody
    public String updateBlog(@PathVariable Integer id,@RequestBody Blog blog, HttpServletResponse response ){
        //身份验证
        Subject subject = SecurityUtils.getSubject();
        String userName=(String)subject.getPrincipals().getPrimaryPrincipal();
        Integer loginId=userService.selectIdByUserName(userName);
        Integer userId=blogService.selectUserIdByBlogId(id);
        if(userId!=loginId){
            return "denied";
        }

        blog.setId(id);
        Integer result=blogService.updateBlog(blog);
        //操作失败返回则错误信息
        if (result==-1){
            response.setStatus(400);
            return "error";
        }else{
            return "ok";
        }
    }

    // 删除博客
    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteBlog(@PathVariable("id")Integer id, HttpServletResponse response ){
        //身份验证
        Subject subject = SecurityUtils.getSubject();
        String userName=(String)subject.getPrincipals().getPrimaryPrincipal();
        Integer loginId=userService.selectIdByUserName(userName);
        Integer userId=blogService.selectUserIdByBlogId(id);
        if(userId!=loginId){
            return "denied";
        }

        Integer result=blogService.deleteBlog(id);
        //操作失败返回则错误信息
        if (result==-1){
            response.setStatus(400);
            return "error";
        }else{
            return "ok";
        }
    }

    @RequestMapping("/getTime")
    @ResponseBody
    public String getTime(@RequestParam String format, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("UTF-8");
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat (format);
        Logger logger = LoggerFactory.getLogger(HomeController.class);
        logger.info(format);
        logger.info(df.format(date));
        return df.format(date);
    }

}