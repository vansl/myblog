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

    // 通过博客id查询用户id
    Integer selectUserIdByBlogId(Integer blogId);

    // 通过用户id查询所有文章
    List<BlogData> selectAll(@Param("published")Boolean published, @Param("userId")Integer userId);

    // 通过博客id查询博客信息
    BlogData selectById(Integer id);

    // 通过分类id查询该分类下的所有博客
    List<BlogData> selectByTypeId(@Param("published")Boolean published,@Param("typeIds")List<Integer> typeIds);

    // 通过博客id查询博客内容
    String selectContentByBlogId(Integer id);

    // 添加文章（包括草稿）
    Integer insertBlog(Blog blog);

    // 通过博客id更新博客字段
    Integer updateBlog(Blog blog);

    // 删除文章
    Integer deleteBlog(Integer id);

    // 通过分类id删除该分类下的所有文章
    Integer deleteByTypeId(Integer typeId);
}
