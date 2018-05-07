package com.vansl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vansl.dto.BlogData;
import com.vansl.entity.BlogComment;
import com.vansl.service.BlogCommentService;
import com.vansl.service.BlogService;
import com.vansl.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

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

    @Autowired
    BlogCommentService blogCommentService;


    @GetMapping("/")
    public  String index(HttpServletRequest request){
        request.setAttribute("welcome","主页");
        request.setAttribute("articleList",blogService.selectAll(1,true,1,Integer.MAX_VALUE).getData());
        request.setAttribute("typeData", JSON.toJSONString(blogTypeService.selectAll(1)));
        //返回index.jsp
        return "index";
    }

    @GetMapping(value="/article",params ={"userId","typeId"})
    public  String index(Integer userId,Integer typeId,HttpServletRequest request){
        request.setAttribute("welcome","博客分类");
        request.setAttribute("articleList",blogService.selectByTypeId(userId,typeId,true,1,Integer.MAX_VALUE).getData());
        request.setAttribute("typeData", JSON.toJSONString(blogTypeService.selectAll(1)));
        //返回index.jsp
        return "index";
    }

    @GetMapping("/article/{id}")
    public  String article(@PathVariable  Integer id, HttpServletRequest request){
        BlogData blogData=blogService.selectById(id);
        if (blogData.getPublished()==1){
            return "denied";
        }
        List<BlogComment> comments=blogCommentService.selectByBlogId(id,1,Integer.MAX_VALUE);
        //转换地址和时间
        for (BlogComment comment:comments){
            try {
                JSONObject addressJson=(JSONObject)JSON.parseObject(comment.getAddress()).get("data");
                comment.setAddress((String)addressJson.get("city")+" "+addressJson.get("isp"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        request.setAttribute("blogData",blogData);
        request.setAttribute("blogContent",blogService.selectContentByBlogId(id));
        request.setAttribute("comments",comments);

        //返回article.jsp
        return "article";
    }

}