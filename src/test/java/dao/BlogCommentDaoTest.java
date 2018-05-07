package dao;

import com.alibaba.fastjson.JSON;
import com.vansl.dao.BlogCommentDao;
import com.vansl.entity.BlogComment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: vansl
 * @create: 18-4-22 下午5:12
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring配置文件
@ContextConfiguration({ "classpath:spring/applicationContext-dao.xml"})
public class BlogCommentDaoTest {

    @Autowired
    BlogCommentDao blogCommentDao;

    @Test
    public void testSelectAll(){
        List<Map> data=blogCommentDao.selectAll(1);
        System.out.println(JSON.toJSONString(data));
    }

    @Test
    public void testSelectByBlogId(){
        List<BlogComment> result=blogCommentDao.selectByBlogId(22);
        System.out.println(JSON.toJSONString(result));

    }
}
