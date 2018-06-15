package top.mcwebsite.blog.controller;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.mcwebsite.blog.domain.Blog;
import top.mcwebsite.blog.domain.Catalog;
import top.mcwebsite.blog.domain.User;
import top.mcwebsite.blog.dto.Response;
import top.mcwebsite.blog.service.BlogService;
import top.mcwebsite.blog.service.CatalogService;
import top.mcwebsite.blog.service.impl.UserServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/u")
public class UserSpaceController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BlogService blogService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserServiceImpl userServiceImpl;

    @Autowired
    private CatalogService catalogService;


    @GetMapping("/{username}")
    public String userSpace(@PathVariable("username") String username, Model model) {
        User user = (User) userServiceImpl.loadUserByUsername(username);
        model.addAttribute("user", user);
        return "redirect:/u/" + username + "/blogs";
    }

    @GetMapping("/{username}/blogs")
    public String getBlogs(@PathVariable("username") String username,
                           @RequestParam(value = "catalog", required = false,defaultValue = "1") Long catalogId,
                           @RequestParam(value = "async", required = false) boolean async,
                           @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
                           Model model) {
        User user = (User) userServiceImpl.loadUserByUsername(username);
        PageInfo<Blog> page = null;
        System.out.println(pageNo + " " + pageSize);
        if (catalogId != null && catalogId > 0) {
            Catalog catalog = catalogService.findCatalogById(catalogId);
            page = blogService.findAll(pageNo, pageSize);
        }

        List<Blog> blogs = page.getList();
        System.out.println(blogs.size());
        List<Catalog> catalogs = catalogService.selectCatalogsByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("catalogs", catalogs);
        model.addAttribute("page", page);
        model.addAttribute("blogs", blogs);
        return (async == true ? "/userspace/u :: #mainContainerRepleace" : "/userspace/u");
    }

    @GetMapping("/{username}/blogs/edit")
    public String createBlog(@PathVariable("username") String username, Model model) {
        User user = (User) userServiceImpl.loadUserByUsername(username);
        List<Catalog> catalogs = catalogService.selectCatalogsByUserId(user.getId());
        model.addAttribute("blog", new Blog());
        model.addAttribute("catalogs", catalogs);
        model.addAttribute("title", "写博客");
        return "userspace/blogedit";
    }

    @PostMapping("/{username}/blogs/edit")
    public ResponseEntity<Response> saveBlog(@PathVariable("username") String username, @RequestBody Blog blog) {
        // 对Catalog进行空处理
        if (blog.getCatalog().getId() == null) {
            return ResponseEntity.ok().body(new Response(false, "未选择分类"));
        }
        try {
            if (blog.getId() == null) {
                User user = (User) userServiceImpl.loadUserByUsername(username);
                blog.setUser(user);
                blog.setCreateTime(LocalDateTime.now());
                blog.setModTime(LocalDateTime.now());
                blogService.saveBlog(blog);
            } else {
                blog.setCreateTime(LocalDateTime.now());
                blogService.update(blog);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }

        logger.info("Blog的id:" + blog.getId());
        String readirectUrl = "/u/" + username + "/blogs/" + blog.getId();
        logger.info(readirectUrl);
        return ResponseEntity.ok().body(new Response(true, "处理成功", readirectUrl));
    }

    @GetMapping("/{username}/blogs/{blogId}")
    public String getBlogById(@PathVariable("username") String username,
                              @PathVariable("blogId") long id, Model model) {
        User principal = null;
        Blog blog = blogService.findById(id);
        if (blog == null) {
            // 出错
            return "/";
        }
        // 每次读取，可以认为阅读量增加1次
        blogService.readNumAdd(id);

        // 判断用户是否是博客的所有者
        boolean isBlogOwner = false;
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal != null && username.equals(principal.getUsername())) {
                isBlogOwner = true;
            }
        }

        model.addAttribute("isBlogOwner", isBlogOwner);
        model.addAttribute("blogModel", blog);
        model.addAttribute("createTime", blog.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm")));
        return "/userspace/blog";
    }

    /**
     * 获取博客编辑页面
     *
     * @param username
     * @param blogId
     * @param model
     * @return
     */
    @GetMapping("/{username}/blogs/edit/{id}")
    public String blogedit(@PathVariable("username") String username,
                           @PathVariable("id") Long blogId, Model model) {
        // 获取用户分类
        User user = (User) userServiceImpl.loadUserByUsername(username);
        List<Catalog> catalogs = catalogService.selectCatalogsByUserId(user.getId());
        model.addAttribute("catalogs", catalogs);
        model.addAttribute("blog", blogService.findById(blogId));
        return "userspace/blogedit";
    }

    @GetMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public String profile(@PathVariable("username") String username, Model model) {
        User user = (User) userServiceImpl.loadUserByUsername(username);
        model.addAttribute("user", user);
        return "userspace/profile";
    }

    @PostMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public String saveProfile(@PathVariable("username") String username, User user) {
        System.out.println(user);
        userServiceImpl.updateByUserId(user);
        return "redirect:/u/" + username + "/profile";
    }

    /**
     * 删除博客
     *
     * @param username
     * @param id
     * @return
     */
    @DeleteMapping("/{username}/blogs/{id}")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<Response> deleteBlog(@PathVariable("username") String username,
                                               @PathVariable("id") Long id) {
        Response response = null;
        // 验证存在性
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(false, "博客不存在"));
        } else {
            blogService.removeById(id);
        }
        String redirectUrl = "/u/" + username + "/blogs";
        return ResponseEntity.ok().body(new Response(true, "处理成功", redirectUrl));
    }


}
