package top.mcwebsite.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mcwebsite.blog.domain.User;
import top.mcwebsite.blog.dto.Response;
import top.mcwebsite.blog.service.UserService;
import top.mcwebsite.blog.util.SendEmail;
import top.mcwebsite.blog.validate.EmailCode;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailCode emailCode;

    @Autowired
    private SendEmail sendEmail;

    @GetMapping
    public ResponseEntity<Response> users() {
        return null;
    }

    @GetMapping("/add")
    public String regsiterFrom(Model model) {

        model.addAttribute("user", new User());
        return "/user/add";
    }

    @PostMapping
    public String register(@Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> errors =  result.getAllErrors();
            for (ObjectError error : errors) {
                FieldError fieldError = (FieldError) error;
                if (!model.containsAttribute(fieldError.getField())) {
                    logger.error(fieldError.getField() + ":" + fieldError.getDefaultMessage());
                    model.addAttribute("BLOG" + fieldError.getField(), fieldError.getDefaultMessage());
                }
            }
            return "/user/add";
        }
        // 查询用户名和邮箱是否存在
        if (userService.isExistUsername(user.getUsername())) {
            model.addAttribute("username", "用户名已经存在");
            return "/user/add";
        }
        if (userService.isExistEmail(user.getEmail())) {
            model.addAttribute("email", "邮箱以存在");
            return "/user/add";
        }

        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userService.saveUser(user)) {
            String activeCode = emailCode.getCode();
            sendEmail.sendEmail(activeCode, user.getUsername(), user.getEmail());
            return "login";
        } else {
            return "login-error";
        }
    }
}
