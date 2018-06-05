package top.mcwebsite.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model) {
        model.addAttribute("title", "标题");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "登录");
        return "login";
    }

    @PostMapping("/login-error")
    public String loginError(Model model) {
        return "login";
    }
}
