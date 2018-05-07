package com.vansl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vansl.dao.BlogDao;
import com.vansl.dao.BlogTypeDao;
import com.vansl.dto.BlogData;
import com.vansl.dto.TableData;
import com.vansl.entity.Blog;
import com.vansl.entity.BlogComment;
import com.vansl.entity.BlogType;
import com.vansl.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: vansl
 * @create: 18-4-25 下午11:29
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

    @Autowired
    private BlogTypeDao blogTypeDao;

    @Override
    public TableData selectAll(Integer userId, Boolean published,Integer offset, Integer limit) {
        TableData result=new TableData();
        // 分页并按照上传时间降序排列
        PageHelper.startPage(offset, limit,"blog.time DESC");
        List<BlogData> data=blogDao.selectAll(published,userId);
        result.setCode(0);
        result.setMsg("ok");
        PageInfo page = new PageInfo(data);
        result.setCount(page.getTotal());
        result.setData(data);
        return result;
    }

    @Override
    public BlogData selectById(Integer id) {
        return  blogDao.selectById(id);
    }

    @Override
    public TableData selectByTypeId(Integer userId,Integer typeId,Boolean published,Integer offset, Integer limit){
        //所有分类数据
        List<BlogType> typeList=blogTypeDao.selectAll(userId);
        //以parentId为索引建立map以查找子分类
        HashMap<Integer,List<Integer>> map=new HashMap<Integer,List<Integer>>();
        for (BlogType type:typeList) {
            if (map.get(type.getParentId())==null){
                map.put(type.getParentId(),new ArrayList<Integer>());
            }
            map.get(type.getParentId()).add(type.getId());
        }
        //保存所有子分类id
        List<Integer> typeIds=new ArrayList<Integer>();
        getChildrenId(typeId,map,typeIds);

        TableData result=new TableData();
        // 分页并按照上传时间降序排列
        PageHelper.startPage(offset, limit,"blog.time DESC");
        List<BlogData> data=blogDao.selectByTypeId(published,typeIds);
        result.setCode(0);
        result.setMsg("ok");
        PageInfo page = new PageInfo(data);
        result.setCount(page.getTotal());
        result.setData(data);
        return result;
    }

    public void getChildrenId(Integer typeId,HashMap<Integer,List<Integer>> map,List<Integer> typeIds){
        //递归处理子分类
        if (map.get(typeId)!=null){
            for (Integer childId:map.get(typeId)) {
                getChildrenId(childId,map,typeIds);
            }
        }
        //删除分类以及所属文章
        typeIds.add(typeId);
    }


    @Override
    public Integer insertBlog(Blog blog) {
        try {
            Integer result=blogDao.insertBlog(blog);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public String selectContentByBlogId(Integer id) {
        return blogDao.selectContentByBlogId(id);
    }

    @Override
    public Integer updateBlog(Blog blog) {
        return blogDao.updateBlog(blog);
    }

    @Override
    public Integer deleteBlog(Integer id) {
        try {
            Integer result=blogDao.deleteBlog(id);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Integer deleteByTypeId(Integer typeId) {
        try {
            Integer result=blogDao.deleteByTypeId(typeId);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }


}
