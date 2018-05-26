package com.vansl.controller;

import com.vansl.dto.TableData;
import com.vansl.service.UserService;
import com.vansl.utils.IPUtil;
import com.vansl.entity.BlogComment;
import com.vansl.service.BlogCommentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: vansl
 * @create: 18-4-23 下午5:07
 */
@Controller
@RequestMapping("/comment")
public class BlogCommentController {

    @Autowired
    BlogCommentService blogCommentService;

    @Autowired
    UserService userService;

    // 查询用户的所有评论
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

        return  blogCommentService.selectAll(userId,offset,limit);
    }

    // 通过博客id查询博客评论
    @GetMapping(params = {"blogId","offset","limit"})
    @ResponseBody
    public List<BlogComment> getByBlogId(Integer blogId, Integer offset, Integer limit){
        List<BlogComment> result=blogCommentService.selectByBlogId(blogId,offset,limit);
        return result;
    }

    // 添加博客评论
    @PostMapping
    @ResponseBody
    public String addBlogComment(@RequestBody BlogComment blogComment, HttpServletRequest request,HttpServletResponse response){
        //获取并设置ip以及地址
        String ip= IPUtil.getIp(request);
        String address=IPUtil.getAddress(ip);
        blogComment.setIp(ip);
        blogComment.setAddress(address);

        Integer result=blogCommentService.insertBlogComment(blogComment);
        if (result==-1){
            response.setStatus(400);
            return "error";
        }else{
            return "ok";
        }
    }

    // 删除博客评论
    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteBlogComment(@PathVariable("id")Integer id,HttpServletResponse response ){
        //身份验证
        Subject subject = SecurityUtils.getSubject();
        String userName=(String)subject.getPrincipals().getPrimaryPrincipal();
        Integer loginId=userService.selectIdByUserName(userName);
        Integer userId=blogCommentService.selectUserIdByCommentId(id);
        if(userId!=loginId){
            return "denied";
        }

        Integer result=blogCommentService.deleteBlogComment(id);
        //操作失败返回则错误信息
        if (result==-1){
            response.setStatus(400);
            return "error";
        }else{
            return "ok";
        }
    }

}
