package top.mcwebsite.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mcwebsite.blog.domain.Catalog;
import top.mcwebsite.blog.mapper.CatalogMapper;
import top.mcwebsite.blog.service.CatalogService;

import java.util.List;

/**
 * Catalog 服务层
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogMapper catalogMapper;

    /**
     * 根据用户id获取目录
     * @param userId
     * @return
     */
    @Override
    public List<Catalog> selectCatalogsByUserId(Long userId) {
        return catalogMapper.selectCatalogByUserId(userId);
    }

    /**
     * 新增一个目录
     * @param catalog
     * @return
     */
    @Override
    public boolean insert(Catalog catalog) {
        return catalogMapper.insert(catalog) == 1;
    }

    /**
     * 更新目录
     * @param catalog
     * @return
     */
    @Override
    public boolean updaeById(Catalog catalog) {
        return catalogMapper.updateById(catalog) == 1;
    }

    /**
     * 删除一个目录
     * @param catalog
     * @return
     */
    @Override
    public boolean deleteById(Catalog catalog) {
        return catalogMapper.deleteById(catalog) == 1;
    }
}
