package top.mcwebsite.blog.domain;

/**
 * 博客的目录
 */
public class Catalog {

    private Long id;        // 目录id
    private Long userId;    // 目录所属用户
    private String name;    // 目录的名称

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
