package com.vansl.dao;

import com.vansl.entity.BlogComment;

import java.util.List;
import java.util.Map;

/**
 * @author: vansl
 * @create: 18-4-21 下午9:00
 */
public interface BlogCommentDao {

    // 通过评论id查询用户id
    Integer selectUserIdByCommentId(Integer commentId);

    // 通过用户id查询所有博客评论
    List<Map> selectAll(Integer userId);

    // 通过博客id查询博客评论
    List<BlogComment> selectByBlogId(Integer blogId);

    // 添加博客评论
    Integer insertBlogComment(BlogComment blogComment);

    // 删除博客评论
    Integer deleteBlogComment(Integer id);

    // 通过博客id删除博客评论
    Integer deleteByBlogId(Integer blogId);
}
