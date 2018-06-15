package top.mcwebsite.blog.service;

import top.mcwebsite.blog.domain.User;

public interface UserService {
    boolean saveUser(User user);
    boolean isExistUsername(String username);
    boolean isExistEmail(String email);
    boolean activeUser(String username);
    boolean updateByUserId(User user);
    User findUserByUsername(String username);
}
