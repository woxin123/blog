package top.mcwebsite.blog.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.mcwebsite.blog.domain.Blog;
import top.mcwebsite.blog.service.BlogService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogMapperTest {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    BlogService blogService;

    @Test
    public void testSelectBlogById() {

        Blog blog = blogMapper.selectById(1L);
        System.out.println(blog);
        System.out.println(blog.getTitle() + ":" + blog.getReadNum());
    }

    @Test
    public void testFindAll() {
        PageHelper.startPage(1, 1, "read_num desc");
        List<Blog> blogs = blogMapper.selectAll();
        System.out.println(blogs);
    }
}
