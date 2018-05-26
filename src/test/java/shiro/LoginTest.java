package shiro;

import com.alibaba.fastjson.JSON;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author: vansl
 * @create: 18-5-23 下午4:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring配置文件
@ContextConfiguration({ "classpath:spring/applicationContext-*.xml","classpath:shiro/applicationContext-shiro.xml"})
@WebAppConfiguration
public class LoginTest {

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;
    //初始化MockMvc对象
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testLogin() throws Exception {
        Map<Object,Object> map=new HashMap<>();
        map.put("username","admin");
        map.put("password","123456");
        map.put("captcha","1");
        String data= JSON.toJSONString(map);
        String result= mockMvc.perform(post("check").
                contentType(MediaType.APPLICATION_JSON).content(data)).
                andDo(print()).
                andReturn().
                getResponse().
                getContentAsString();
        System.out.println(result);
    }

}
