package top.mcwebsite.blog.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails, Serializable {

    private Long id; // 用户的唯一标识

    @NotEmpty(message = "用户名不能为空")
    @Size(min = 2, max = 20, message = "用户名必须在2到20之间")
    private String username; // 用户名

    @NotEmpty(message = "邮箱不能为空")
    @Size(max = 50)
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 3, max = 20, message = "密码必须在3到20之间")
    private String password;

    private String avatr; // 用户头像的url

    private Integer userStatus;

    private boolean lockUser;

    private List<Authority> authorities;


    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 需要将List<AuthorityMapper> 转成 List<SimpleGrantedAuthority>,否则前端拿不到角色列表名称
        List<SimpleGrantedAuthority> simpleAuthorities = new ArrayList<>();
        for (GrantedAuthority authority : this.authorities) {
            simpleAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return simpleAuthorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setEncodePassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(password);
        this.password = encodePassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return lockUser;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatr() {
        return avatr;
    }

    public void setAvatr(String avatr) {
        this.avatr = avatr;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public boolean isLockUser() {
        return lockUser;
    }

    public void setLockUser(boolean lockUser) {
        this.lockUser = lockUser;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
        if (userStatus == 1)
            lockUser = false;
        else
            lockUser = true;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
