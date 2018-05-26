package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: vansl
 * @create: 18-5-8 下午7:08
 */
public class ShiroTest {
    @Test
    public void testLogin() {
        //加载配置文件并获取工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro.ini");
        //获取安全管理器实例
        SecurityManager manager = factory.getInstance();
        //将安全管理器放入全局对象
        SecurityUtils.setSecurityManager(manager);
        //通过安全管理器生成Subject对象
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        //将用户数据token传递到Realm
        subject.login(token);
        //判断用户是否认证成功
        Assert.assertEquals(true, subject.isAuthenticated());
    }
}
