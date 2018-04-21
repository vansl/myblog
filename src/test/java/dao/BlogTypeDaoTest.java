package dao;

import com.vansl.dao.BlogTypeDao;
import com.vansl.entity.BlogType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author: vansl
     * @create: 18-4-14 下午1:15
 */


@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring配置文件
@ContextConfiguration({ "classpath:spring/applicationContext-dao.xml"})
public class BlogTypeDaoTest {

    @Autowired
    private BlogTypeDao blogTypeDao;

    @Test
    public void testSelectAll() throws Exception {
        List<BlogType> result= blogTypeDao.selectAll(1);
        for (BlogType blogType:result) {
            System.out.println(blogType.getTypeName());
        }
    }

    @Test
    public void testUpdateBlogType() throws Exception {
        BlogType type=new BlogType();
        type.setTypeName("JAVAScript");
        type.setId(1);
        blogTypeDao.updateBlogType(type);
    }

    @Test
    public void testDeleteBlogType() throws Exception {
        assertEquals(new Integer(1),blogTypeDao.deleteBlogType(2));
    }

}
