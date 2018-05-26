package jedis;

import com.vansl.utils.JedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author: vansl
 * @create: 18-5-19 下午11:16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-redis.xml"})
public class JedisUtilTest {

    @Autowired
    JedisUtil jedisUtil;

    @Test
    public void testSetAndDel(){
        jedisUtil.set("testJedis","testSucceed");
        assertEquals(jedisUtil.get("testJedis"),"testSucceed");
        jedisUtil.del("testRedis");
    }

    @Test
    public void testExpire() throws Exception{
        jedisUtil.set("testExpire","testSucceed");
        jedisUtil.expire("testExpire",3);
        assertEquals(jedisUtil.get("testExpire"),"testSucceed");
        Thread.sleep(3000);
        assertEquals(jedisUtil.get("testExpire"),null);
    }
}
