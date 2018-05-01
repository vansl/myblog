package com.vansl.service;

import com.vansl.dto.TableData;

/**
 * @author: vansl
 * @create: 18-4-25 下午11:25
 */

public interface BlogService {

    // 通过用户id查询所有博客
    public TableData selectAll(Integer userId,Boolean published,Integer offset, Integer limit);

    // 通过分类id查询该分类下的所有博客

    // 添加博客

    // 通过博客id查询博客正文

    // 通过博客id更新博客字段

    // 删除博客

    // 通过分类id删除该分类下的所有博客（除content字段）
}
