package top.mcwebsite.blog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.TimeUnit;

@Component
public class SendEmail {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${spring.mail.username}")
    private String sender;

    public boolean sendEmail(String activeCode, String username, String email) {
        String prefixUrl = "http://localhost:8080";
        String url = "/account/" + username +"?activeCode=" + activeCode;
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(email);
            helper.setSubject("注册激活账号");
            StringBuffer sb = new StringBuffer();
            sb.append("<p><string>单击下方链接激活账号<string></p>")
                    .append("<a href='" + prefixUrl + url + "'>一个神奇的链接</a>");
            sb.append("<br/>链接有效时间3小时");
            helper.setText(sb.toString(), true);
            redisTemplate.opsForValue().set("BLOG" + username, activeCode, 3, TimeUnit.HOURS);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        mailSender.send(message);
        return true;
    }
}
