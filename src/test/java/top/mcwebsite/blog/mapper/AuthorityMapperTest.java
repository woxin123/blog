package top.mcwebsite.blog.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.mcwebsite.blog.domain.Authority;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorityMapperTest {
    @Autowired
    private AuthorityMapper authorityMapper;

    @Test
    public void testSelectByUserId() {
        List<Authority> authorities = authorityMapper.selectByUserId(1L);
        assertNotNull(authorities);
        authorities.stream().forEach((o1)-> System.out.println(o1.getAuthority()));
    }
}
