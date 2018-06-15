package top.mcwebsite.blog.mapper;

import top.mcwebsite.blog.domain.User;

public interface UserMapper {
    User selectUserById(Long id);
    User selectUserByUsernameOrEmail(String condition);

    Integer insert(User user);

    User selectUserByUsername(String username);

    User selectUserByEmail(String email);

    int activeUser(String username);

    int update(User user);
}
