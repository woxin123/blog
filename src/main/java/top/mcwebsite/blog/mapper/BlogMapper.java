package top.mcwebsite.blog.mapper;

import org.apache.ibatis.annotations.Param;
import top.mcwebsite.blog.domain.Blog;

import java.util.List;

public interface BlogMapper {
    /**
     * 新增博客
     * @param blog
     * @return
     */
    int insert(Blog blog);

    /**
     * 根据用户id获取博客
     * @param userId
     * @return
     */
    List<Blog>  selectByUserId(Long userId);

    /**
     * 根据用户id和类别的id获取博客
     * @param userId
     * @param catalogId
     * @return
     */
    List<Blog> selectByUserIdAndCatalogId(@Param("userId") Long userId, @Param("catalogId") Long catalogId);

    /**
     * 根据博客id删除博客
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 通过id查询博客
     * @param id
     * @return
     */
    Blog selectById(Long id);

    /**
     * 更新阅读量
     * @param id
     * @param readNum
     * @return
     */
    int updateReadNumById(@Param("id") Long id, @Param("readNum") Integer readNum);

    /**
     * 获取所有内容
     * @return
     */
    List<Blog> selectAll();


    /**
     * 更新博客
     * @param blog
     * @return
     */
    int update(Blog blog);

}
