package service;

import com.alibaba.fastjson.JSON;
import com.vansl.dto.TableData;
import com.vansl.entity.BlogComment;
import com.vansl.service.BlogCommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.Assert.assertEquals;

/**
 * @author: vansl
 * @create: 18-4-23 下午3:40
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring配置文件
@ContextConfiguration({ "classpath:spring/applicationContext-dao.xml","classpath:spring/applicationContext-service.xml"})
public class BlogCommentServiceTest {

    @Autowired
    BlogCommentService service;

    @Test
    public void testSelectAll(){
        TableData data=service.selectAll(1,2,10);
        System.out.println(JSON.toJSONString(data));
    }

    @Test
    public void testInsertBlogComment(){
        BlogComment comment=new BlogComment();
        comment.setContent("test");
        comment.setIp("127.0.0.1");
        comment.setName("vansl");
        comment.setContact("kkk@gmail.com");
        comment.setBlogId(1);
        assertEquals(new Integer(1),service.insertBlogComment(comment));
    }


    @Test
    public void testDeleteBlogComment(){
        assertEquals(new Integer(1),service.deleteBlogComment(1));
    }

    @Test
    public void testDeleteByBlogId(){
        assertEquals(new Integer(10),service.deleteByBlogId(22));
    }
}
