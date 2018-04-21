package service;

import com.alibaba.fastjson.JSON;
import com.vansl.dto.TypeTreeNode;
import com.vansl.entity.BlogType;
import com.vansl.service.BlogTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author: vansl
 * @create: 18-4-15 下午4:28
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring配置文件
@ContextConfiguration({ "classpath:spring/applicationContext-dao.xml","classpath:spring/applicationContext-service.xml"})
public class BlogTypeServiceTest {
    @Autowired
    BlogTypeService blogTypeService;

    @Test
    public void testSelectById(){
        TypeTreeNode result= blogTypeService.selectById(5);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testSelectAll() throws Exception {
        List<TypeTreeNode> result= blogTypeService.selectAll(1);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testInsertBlogType() throws Exception {
        BlogType type=new BlogType();
        type.setUserId(1);
        type.setParentId(0);
        type.setTypeName("前端");
        assertEquals(new Integer(1),blogTypeService.insertBlogType(type));
        //同个父分类下不允许有同名子分类
        assertEquals(new Integer(-1),blogTypeService.insertBlogType(type));

        type.setParentId(1);
        assertEquals(new Integer(1),blogTypeService.insertBlogType(type));
    }

    @Test
    public void testDelete() throws Exception {
        BlogType type=new BlogType();
        type.setUserId(1);
        type.setId(3);
        blogTypeService.deleteBlogType(type);
    }
}