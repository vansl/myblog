package com.vansl.service;

import com.vansl.dto.BlogData;
import com.vansl.dto.TableData;
import com.vansl.entity.Blog;

/**
 * @author: vansl
 * @create: 18-4-25 下午11:25
 */

public interface BlogService {

    // 通过用户id查询所有博客
    TableData selectAll(Integer userId,Boolean published,Integer offset, Integer limit);

    // 通过博客id查询博客信息
    BlogData selectById(Integer id);

    // 通过分类id查询该分类下的所有博客
    TableData selectByTypeId(Integer userId,Integer typeId,Boolean published,Integer offset, Integer limit);

    // 添加博客
    Integer insertBlog(Blog blog);

    // 通过博客id查询博客正文
    String selectContentByBlogId(Integer id);

    // 通过博客id更新博客字段
    Integer updateBlog(Blog blog);

    // 删除博客
    Integer deleteBlog(Integer id);

    // 通过分类id删除该分类下的所有博客
    Integer deleteByTypeId(Integer typeId);

}
