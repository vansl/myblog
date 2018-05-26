package com.vansl.service;

import com.vansl.dto.TableData;
import com.vansl.entity.BlogComment;

import java.util.List;

/**
 * @author: vansl
 * @create: 18-4-22 上午11:48
 */
public interface BlogCommentService {

    // 通过评论id查询用户id
    Integer selectUserIdByCommentId(Integer commentId);

    // 通过用户id查询所有博客评论
    TableData selectAll(Integer userId, Integer offset, Integer limit);

    // 通过博客id查询博客评论
    List<BlogComment> selectByBlogId(Integer blogId,Integer offset,Integer limit);

    // 添加博客评论
    Integer insertBlogComment(BlogComment blogComment);

    // 删除博客评论
    Integer deleteBlogComment(Integer id);

    // 通过博客id删除博客评论
    Integer deleteByBlogId(Integer blogId);
}
