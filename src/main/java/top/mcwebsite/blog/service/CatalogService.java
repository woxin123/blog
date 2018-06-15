package top.mcwebsite.blog.service;

import top.mcwebsite.blog.domain.Catalog;

import java.util.List;

public interface CatalogService {

    List<Catalog> selectCatalogsByUserId(Long userId);

    boolean insert(Catalog catalog);

    boolean updaeById(Catalog catalog);

    boolean deleteById(Catalog catalog);

    Catalog findCatalogById(Long id);

}
