package top.mcwebsite.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.mcwebsite.blog.domain.User;
import top.mcwebsite.blog.service.UserService;

/**
 * 账号的激活
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public String actvie(@PathVariable String username,  String activeCode, Model model) {
        // 查看username是否存在
        String activeCodeInRedis = redisTemplate.opsForValue().get("BLOG" + username);
        if (activeCode.equals(activeCodeInRedis)) {
            if (userService.activeUser(username)) {
                redisTemplate.delete("BLOG" + username);
                return "login";
            }
        }
        User user = userService.findUserByUsername(username);
        user.setPassword(null);
        model.addAttribute("user", user);
        model.addAttribute("regsiter", "激活失败，请检查邮箱是否正确");
        return "/user/add";
    }
}
