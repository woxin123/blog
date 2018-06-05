package top.mcwebsite.blog.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * 权限的实体
 */
public class Authority implements GrantedAuthority {

    private Long id;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
