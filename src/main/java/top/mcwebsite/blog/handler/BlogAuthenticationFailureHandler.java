package top.mcwebsite.blog.handler;

import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.mail.AuthenticationFailedException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class BlogAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof UsernameNotFoundException) {
            request.setAttribute("username", "用户不存在");
        } else if (exception instanceof BadCredentialsException) {
            request.setAttribute("password", "密码错误");
        } else if (exception instanceof AccountStatusException) {
            request.setAttribute("username", "账户没有被激活");
        }
        request.getRequestDispatcher("/login-error").forward(request, response);
    }
}
