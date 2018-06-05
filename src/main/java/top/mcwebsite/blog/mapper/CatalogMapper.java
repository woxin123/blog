package top.mcwebsite.blog.mapper;

import top.mcwebsite.blog.domain.Catalog;

import java.util.List;

public interface CatalogMapper {

    /**
     * 根据用户Id获取目录
     * @param userId
     * @return
     */
    List<Catalog> selectCatalogByUserId(Long userId);

    /**
     * 添加目录
     * @param catalog
     * @return
     */
    Integer insert(Catalog catalog);

    /**
     * 重命名目录
     * @param catalog
     * @return
     */
    Integer updateById(Catalog catalog);

    /**
     * 根据用户id删除目录
     * @param catalog
     * @return
     */
    Integer deleteById(Catalog catalog);

}
