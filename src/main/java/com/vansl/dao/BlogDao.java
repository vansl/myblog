package com.vansl.dao;

import com.vansl.dto.BlogData;
import com.vansl.entity.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: vansl
 * @create: 18-4-25 下午10:43
 */
public interface BlogDao {

    // 通过用户id查询所有文章
    List<BlogData> selectAll(@Param("published")Boolean published, @Param("userId")Integer userId);

    // 更新文章

    // 删除文章

    // 通过分类id删除该分类下的所有文章

    // 添加文章




    // 通过分类id查询所有文章
    List<Blog> selectByTypeId(Integer typeId);


}
