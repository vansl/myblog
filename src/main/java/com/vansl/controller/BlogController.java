package com.vansl.controller;

import com.vansl.dto.TableData;
import com.vansl.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: vansl
 * @create: 18-4-27 下午2:25
 */

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogService blogService;

    // 查询用户的所有文章（包括草稿）
    @GetMapping(params = {"userId","offset","limit"})
    @ResponseBody
    public TableData getAllData(Integer userId, Integer offset, Integer limit){
        /*登录验证功能尚未实现
         *  Integer loginId=redisUtil.getUser().getUserId();
         *  if(loginId!=userId){
         *      return "denied";
         *  }
         */
        return  blogService.selectAll(userId,false,offset,limit);
    }
}
