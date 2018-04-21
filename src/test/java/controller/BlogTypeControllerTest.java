package controller;

import com.alibaba.fastjson.JSON;
import com.vansl.controller.BlogTypeController;
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

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author: vansl
 * @create: 18-4-15 下午9:31
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring配置文件
@ContextConfiguration({ "classpath:spring/applicationContext-dao.xml","classpath:spring/applicationContext-service.xml","classpath:spring/spring-mvc.xml"})
@WebAppConfiguration
public class BlogTypeControllerTest {
    @Autowired
    BlogTypeController blogTypeController;

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;
    //初始化MockMvc对象
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGetAllData() throws Exception {
        String result= mockMvc.perform(get("/type?userId=1")).
                                andDo(print()).
                                andReturn().
                                getResponse().
                                getContentAsString();
        System.out.println(result);
    }

    @Test
    public void testDeleteBlogType() throws Exception {
        Map<Object,Object> map=new HashMap<>();
        map.put("userId",1);
        String data= JSON.toJSONString(map);
        String result= mockMvc.perform(delete("/type/242").
                contentType(MediaType.APPLICATION_JSON).content(data)).
                andDo(print()).
                andReturn().
                getResponse().
                getContentAsString();
        System.out.println(result);
    }
}
