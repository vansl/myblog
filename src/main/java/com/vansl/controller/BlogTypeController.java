package com.vansl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vansl.dto.TypeTreeNode;
import com.vansl.entity.BlogType;
import com.vansl.service.BlogTypeService;
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

    // 通过id查询博客分类
    @GetMapping("/{id}")
    @ResponseBody
    public TypeTreeNode getTypeById(@PathVariable("id")Integer id){
        return blogTypeService.selectById(id);
    }

    // 查询用户的所有分类数据
    @GetMapping(params = {"userId"})
    @ResponseBody
    public List<TypeTreeNode> getAllData(@RequestParam("userId")Integer userId){
        return blogTypeService.selectAll(userId);
    }

    // 通过博客id查询博客分类
    @GetMapping(params = {"blogId"})
    @ResponseBody
    public BlogType getTypeByBlogId(@RequestParam("blogId")Integer blogId){
        //调用service执行操作
        return blogTypeService.selectByBlogId(blogId);
    }

    // 添加博客分类
    @PostMapping
    @ResponseBody
    public String addBlogType(@RequestBody BlogType type, HttpServletResponse response){
        /*登录验证功能尚未实现
         *  Integer loginId=redisUtil.getUser().getUserId();
         *  if(loginId!=type.getUserId()){
         *      return "denied";
         *  }
         */

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
        /*登录验证功能尚未实现
         *  Integer loginId=redisUtil.getUser().getUserId();
         *  if(loginId!=type.getUserId()){
         *      return "denied";
         *  }
         */

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
    public String deleteBlogType(@PathVariable("id")Integer id,@RequestBody BlogType type, HttpServletResponse response){
        /*登录验证功能尚未实现*
         *  Integer loginId=redisUtil.getUser().getUserId();
         *  if(loginId!=type.getUserId()){
         *      return "denied";
         *  }
         */
        type.setId(id);
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