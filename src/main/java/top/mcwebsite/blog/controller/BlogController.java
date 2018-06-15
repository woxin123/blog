package top.mcwebsite.blog.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import top.mcwebsite.blog.domain.Blog;
import top.mcwebsite.blog.service.BlogService;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/userspace/blogedit")
    public ModelAndView blogedit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/userspace/blogedit");
        return modelAndView;
    }

    @GetMapping("/blogs")
    public String blogs(@RequestParam(value = "order", required = false, defaultValue = "new") String order,
                        @RequestParam(value = "async", required = false) boolean async,
                        @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
                        Model model) {
        System.out.println(pageNo + " " + pageSize);
        PageInfo<Blog> page = blogService.findAll(pageNo, pageSize);
        if (pageNo == 0)
            page.setIsFirstPage(true);
        List<Blog> blogs = page.getList();
        model.addAttribute("blogs", blogs);
        model.addAttribute("page", page);
        return (async==true?"/index :: #mainContainerRepleace":"/index");
    }
}
