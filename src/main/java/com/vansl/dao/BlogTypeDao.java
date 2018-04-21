package com.vansl.dao;

import com.vansl.entity.BlogType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: vansl
 * @create: 18-4-12 下午5:14
 */
public interface BlogTypeDao {

    // 通过id查询博客分类
    BlogType selectById(Integer id);

    // 通过用户id查询所有分类数据
    List<BlogType> selectAll(Integer userId);

    // 通过博客id查询博客分类
    BlogType selectByBlogId(Integer blogId);

    // 添加博客分类
    Integer insertBlogType(BlogType blogType);

    // 更新博客分类
    Integer updateBlogType(BlogType blogType);

    // 删除博客分类
    Integer deleteBlogType(Integer id);

}
