package shiro;

import com.vansl.utils.AliyunOSSUtil;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author: vansl
 * @create: 18-5-10 下午5:01
 */

// 加载spring配置文件
public class AliyunOSSTest {

    @Test
    public void testUpload() throws  Exception{
            File file=new File("/home/vansl/test.txt");
            //AliyunOSSUtil.createFolder("vanslblog","test");
            String url=AliyunOSSUtil.upload(new FileInputStream(file),file.getName(),"vanslblog","a/b/c");
            System.out.println(url);
    }

    @Test
    public void testDelete(){
        AliyunOSSUtil.deleteFolder("vanslblog","a/b");
    }
}