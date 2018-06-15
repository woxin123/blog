package top.mcwebsite.blog.service;

import com.github.pagehelper.PageInfo;
import top.mcwebsite.blog.domain.Blog;

import java.util.List;

public interface BlogService {

    boolean saveBlog(Blog blog);

    boolean removeById(Long id);

    Blog findById(Long id);

    List<Blog> findByUserId(Long userId);

    List<Blog> findByUserIdAndCatalogId(Long userId, Long catalogId);

    void readNumAdd(Long id);

    PageInfo<Blog> findAll(int pageNo, int pageSize);

    boolean update(Blog blog);
}
