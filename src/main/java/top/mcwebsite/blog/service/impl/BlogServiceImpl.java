package top.mcwebsite.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mcwebsite.blog.domain.Blog;
import top.mcwebsite.blog.mapper.BlogMapper;
import top.mcwebsite.blog.service.BlogService;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public boolean saveBlog(Blog blog) {
        return blogMapper.insert(blog) == 1;
    }

    @Override
    public boolean removeById(Long id) {
        return blogMapper.deleteById(id) == 1;
    }

    @Override
    public Blog findById(Long id) {
        return blogMapper.selectById(id);
    }

    @Override
    public List<Blog> findByUserId(Long userId) {
        List<Blog> blogs = blogMapper.selectByUserId(userId);
        return blogs;
    }

    @Override
    public List<Blog> findByUserIdAndCatalogId(Long userId, Long catalogId) {
        List<Blog> blogs = blogMapper.selectByUserIdAndCatalogId(userId, catalogId);
        return blogs;
    }

    @Override
    public void readNumAdd(Long id) {
        Blog blog = blogMapper.selectById(id);
        if (blog == null) {
            logger.error("id为：" + id + "没有找到！");
            return;
        }
        logger.info(blog.getReadNum() + " azscfazsc");
        blogMapper.updateReadNumById(id, blog.getReadNum() + 1);
    }

    @Override
    public PageInfo<Blog> findAll(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        PageHelper.orderBy("read_num desc");
        List<Blog> blogs = blogMapper.selectAll();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    @Override
    public boolean update(Blog blog) {
        return blogMapper.update(blog) == 1;
    }


}
