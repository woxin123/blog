package top.mcwebsite.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.mcwebsite.blog.domain.User;
import top.mcwebsite.blog.mapper.UserMapper;
import top.mcwebsite.blog.service.UserService;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.selectUserByUsernameOrEmail(username);
        return user;
    }

    @Override
    public boolean saveUser(User user) {
        return userMapper.insert(user) == 1;
    }

    @Override
    public boolean isExistUsername(String username) {
        return userMapper.selectUserByUsername(username) != null;
    }

    @Override
    public boolean isExistEmail(String email) {
        return userMapper.selectUserByEmail(email) != null;
    }

    @Override
    public boolean activeUser(String username) {
        return userMapper.activeUser(username) == 1;
    }

    @Override
    public boolean updateByUserId(User user) {
        return userMapper.update(user) == 1;
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }
}
