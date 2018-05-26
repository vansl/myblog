package com.vansl.controller;

import com.vansl.dto.TypeTreeNode;
import com.vansl.entity.BlogType;
import com.vansl.service.BlogTypeService;
import com.vansl.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: vansl
 * @create: 18-4-15 下午8:44
 */

@Controller
@RequestMapping("/type")
public class BlogTypeController {

    @Autowired
    BlogTypeService blogTypeService;

    @Autowired
    UserService userService;

    // 通过id查询博客分类
    @GetMapping("/{id}")
    @ResponseBody
    public TypeTreeNode getTypeById(@PathVariable("id")Integer id){
        return blogTypeService.selectById(id);
    }

    // 查询用户的所有分类数据
    @GetMapping
    @ResponseBody
    public List<TypeTreeNode> getAllData(){
        //身份验证
        Subject subject = SecurityUtils.getSubject();
        String userName=(String)subject.getPrincipals().getPrimaryPrincipal();
        Integer loginId=userService.selectIdByUserName(userName);

        return blogTypeService.selectAll(loginId);
    }

    // 通过博客id查询博客分类
    @GetMapping(params = {"blogId"})
    @ResponseBody
    public BlogType getTypeByBlogId(@RequestParam Integer blogId){
        //调用service执行操作
        return blogTypeService.selectByBlogId(blogId);
    }

    // 添加博客分类
    @PostMapping
    @ResponseBody
    public String addBlogType(@RequestBody BlogType type, HttpServletResponse response){
        //身份验证
        Subject subject = SecurityUtils.getSubject();
        String userName=(String)subject.getPrincipals().getPrimaryPrincipal();
        Integer loginId=userService.selectIdByUserName(userName);
        type.setUserId(loginId);

        //调用service执行操作
        Integer result=blogTypeService.insertBlogType(type);
        //操作失败返回则错误信息
        if (result==-1){
            response.setStatus(400);
            return "class limited";
        }else if(result==0){
            response.setStatus(400);
            return "error";
        }else{
            return "ok";
        }
    }

    // 更新博客分类
    @PutMapping
    @ResponseBody
    public String updateBlogType(@RequestBody BlogType type, HttpServletResponse response){
        //身份验证
        Subject subject = SecurityUtils.getSubject();
        String userName=(String)subject.getPrincipals().getPrimaryPrincipal();
        Integer loginId=userService.selectIdByUserName(userName);
        Integer userId=blogTypeService.selectUserIdByTypeId(type.getId());
        if(userId!=loginId){
            return "denied";
        }

        //调用service执行操作
        Integer result=blogTypeService.updateBlogType(type);
        //操作失败返回则错误信息
        if (result==0){
            response.setStatus(400);
            return "error";
        }else{
            return "ok";
        }
    }

    // 删除博客分类
    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteBlogType(@PathVariable("id")Integer id, HttpServletResponse response){
        //身份验证
        Subject subject = SecurityUtils.getSubject();
        String userName=(String)subject.getPrincipals().getPrimaryPrincipal();
        Integer loginId=userService.selectIdByUserName(userName);
        Integer userId=blogTypeService.selectUserIdByTypeId(id);
        if(userId!=loginId){
            return "denied";
        }

        BlogType type=new BlogType();
        type.setId(id);
        type.setUserId(userId);

        //调用service执行操作
        Integer result=blogTypeService.deleteBlogType(type);
        //操作失败返回则错误信息
        if (result==-1){
            response.setStatus(400);
            return "error";
        }else{
            return "ok";
        }
    }
}