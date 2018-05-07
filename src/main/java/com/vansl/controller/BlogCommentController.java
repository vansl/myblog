package com.vansl.controller;

import com.alibaba.fastjson.JSONObject;
import com.vansl.dto.TableData;
import com.vansl.utils.IPUtil;
import com.vansl.entity.BlogComment;
import com.vansl.service.BlogCommentService;
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

    // 查询用户的所有评论
    @GetMapping(params = {"userId","offset","limit"})
    @ResponseBody
    public TableData getAllData(Integer userId, Integer offset, Integer limit){
        /*登录验证功能尚未实现
         *  Integer loginId=redisUtil.getUser().getUserId();
         *  if(loginId!=userId){
         *      return "denied";
         *  }
         */
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
    public String deleteBlogComment(@PathVariable("id")Integer id,@RequestBody String data,HttpServletResponse response ){
        // 把请求数据转换成请求对象
        JSONObject json=JSONObject.parseObject(data);
        Integer userId=(Integer) json.get("userId");
        /*登录验证功能尚未实现
         *  Integer loginId=redisUtil.getUser().getUserId();
         *  if(loginId!=userId){
         *      return "denied";
         *  }
         */

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
