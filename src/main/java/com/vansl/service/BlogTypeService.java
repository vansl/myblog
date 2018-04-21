package com.vansl.service;

import com.vansl.dto.TypeTreeNode;
import com.vansl.entity.BlogType;

import java.util.List;

/**
 * @author: vansl
 * @create: 18-4-15 下午4:10
 */
public interface BlogTypeService {

    // 通过id查询博客分类
    TypeTreeNode selectById(Integer id);

    // 通过用户id查询所有分类数据
    List<TypeTreeNode> selectAll(Integer userId);

    // 通过博客id查询博客分类
    BlogType selectByBlogId(Integer blogId);

    // 添加博客分类
    Integer insertBlogType(BlogType blogType);

    // 更新博客分类
    Integer updateBlogType(BlogType blogType);

    // 删除博客分类
    Integer deleteBlogType(BlogType blogType);
}
