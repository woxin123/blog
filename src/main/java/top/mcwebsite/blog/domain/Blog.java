package top.mcwebsite.blog.domain;

import java.time.LocalDateTime;
import com.github.rjeschke.txtmark.Processor;
public class Blog {
    private Long id;            // Blog id
    private User user;          // Blog的用户
    private Catalog catalog;    // catalog目录
    private String title;       // 博客的title
    private String tags;        // 标签
    private String summary;     // 摘要
    private String content;     // 博客内容
    private String htmlContent; // 博客的html内容
    private LocalDateTime createTime;   // 博客创建的时间
    private LocalDateTime modTime;   // 博客修改的时间
    private int readNum = 0;        // 阅读量
    private int commentNum = 0;     // 评论的数量
    private int likeNum = 0;        // 点赞数量
    private int dislikeNum = 0;     // 吐槽数量

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModTime() {
        return modTime;
    }

    public void setModTime(LocalDateTime modTime) {
        this.modTime = modTime;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getDislikeNum() {
        return dislikeNum;
    }

    public void setDislikeNum(int dislikeNum) {
        this.dislikeNum = dislikeNum;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", user=" + user +
                ", catalog=" + catalog +
                ", title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", htmlContent='" + htmlContent + '\'' +
                ", createTime=" + createTime +
                ", modTime=" + modTime +
                ", readNum=" + readNum +
                ", commentNum=" + commentNum +
                ", likeNum=" + likeNum +
                ", dislikeNum=" + dislikeNum +
                '}';
    }
}
