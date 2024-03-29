package com.stone.user.web;

import com.stone.user.config.PatternProperties;
import com.stone.user.pojo.User;
import com.stone.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
//@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

//    @Value("${pattern.dateformat}")
//    private String dateformat;

    @Autowired
    private PatternProperties properties;


    @GetMapping("/now")
    public String now() {
//        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat));
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(properties.getDateformat()));
    }

    @GetMapping("prop")
    public PatternProperties properties() {
        return properties;
    }

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id, @RequestHeader(value = "Slogan", required = false) String slogan) throws InterruptedException {
        if (id == 1) {
            // 休眠 触发熔断
            Thread.sleep(60);
        } else if (id == 2) {
            throw new RuntimeException("故意抛异常 触发异常比例熔断");
        }
        return userService.queryById(id);
    }
}
