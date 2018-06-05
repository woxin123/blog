package top.mcwebsite.blog.validate;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EmailCode {

    public String getCode() {
        return UUID.randomUUID().toString();
    }
}
