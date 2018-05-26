package com.vansl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vansl.dao.BlogCommentDao;
import com.vansl.dto.TableData;
import com.vansl.entity.BlogComment;
import com.vansl.service.BlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: vansl
 * @create: 18-4-22 下午3:06
 */
@Service
public class BlogCommentServiceImpl implements BlogCommentService {

    @Autowired
    BlogCommentDao blogCommentDao;

    @Override
    public Integer selectUserIdByCommentId(Integer commentId) {
        return blogCommentDao.selectUserIdByCommentId(commentId);
    }

    @Override
    public TableData selectAll(Integer userId, Integer offset, Integer limit) {
        TableData result=new TableData();
        // 分页并按照评论时间降序排列
        PageHelper.startPage(offset, limit,"blog_comment.time DESC");
        List<Map> data=blogCommentDao.selectAll(userId);
        result.setCode(0);
        result.setMsg("ok");
        PageInfo page = new PageInfo(data);
        result.setCount(page.getTotal());
        result.setData(data);
        return result;
    }

    @Override
    public List<BlogComment> selectByBlogId(Integer blogId,Integer offset,Integer limit) {
        // 分页并按照评论时间降序排列
        PageHelper.startPage(offset, limit,"blog_comment.time DESC");
        return blogCommentDao.selectByBlogId(blogId);
    }

    @Override
    public Integer insertBlogComment(BlogComment blogComment) {
        try {
            Integer result=blogCommentDao.insertBlogComment(blogComment);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Integer deleteBlogComment(Integer id) {
        try {
            Integer result=blogCommentDao.deleteBlogComment(id);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Integer deleteByBlogId(Integer blogId) {
        try {
            Integer result=blogCommentDao.deleteByBlogId(blogId);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}