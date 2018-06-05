package top.mcwebsite.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.mcwebsite.blog.domain.Authority;

import java.util.List;


public interface AuthorityMapper {

    List<Authority> selectByUserId(Long userId);
}
