package com.vansl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vansl.dao.BlogDao;
import com.vansl.dto.BlogData;
import com.vansl.dto.TableData;
import com.vansl.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: vansl
 * @create: 18-4-25 下午11:29
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

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

}
