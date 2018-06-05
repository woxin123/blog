package top.mcwebsite.blog.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import top.mcwebsite.blog.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testSelectById() {
        User user = userMapper.selectUserById(1L);
        Assert.assertNotNull(user);
        System.out.println(user.getAuthorities());
    }

    @Test
    public void testSelectUserByUsernameOrEmail() {
        User user = userMapper.selectUserByUsernameOrEmail("woxin");
        Assert.assertNotNull(user);
        System.out.println(user.getUserStatus());
    }

    @Test
    public void passwordEncoder() {
        System.out.println(passwordEncoder.encode("123456"));
    }
}
