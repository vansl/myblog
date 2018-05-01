package controller;

import com.alibaba.fastjson.JSON;
import com.vansl.controller.BlogCommentController;
import com.vansl.entity.BlogComment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author: vansl
 * @create: 18-4-24 下午11:15
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring配置文件
@ContextConfiguration({ "classpath:spring/applicationContext-dao.xml","classpath:spring/applicationContext-service.xml","classpath:spring/spring-mvc.xml"})
@WebAppConfiguration
public class BlogCommentControllerTest {

    @Autowired
    BlogCommentController controller;

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;
    //初始化MockMvc对象
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testAddBlogComment() throws Exception {
        BlogComment comment=new BlogComment();
        comment.setName("vansl");
        comment.setBlogId(22);
        comment.setContact("google@gmail.com");
        comment.setTime(new Date());
        comment.setContent("test");
        String data= JSON.toJSONString(comment);
        String result= mockMvc.perform(post("/comment").
                contentType(MediaType.APPLICATION_JSON).content(data)).
                andDo(print()).
                andReturn().
                getResponse().
                getContentAsString();
        System.out.println(result);
    }
}
