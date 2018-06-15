package top.mcwebsite.blog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import top.mcwebsite.blog.handler.BlogAuthenticationFailureHandler;

/**
 * 安全配置类
 */
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法安全设置
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BlogAuthenticationFailureHandler blogAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .failureHandler(blogAuthenticationFailureHandler)
                .and()
                .rememberMe()
                .and()
                .authorizeRequests()
                .antMatchers("/userspace/blogedit")
                .hasRole("USER")
                .and()
                .csrf().disable();
    }
}
