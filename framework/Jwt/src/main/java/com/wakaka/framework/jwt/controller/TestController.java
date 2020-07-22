package com.wakaka.framework.jwt.controller;

import com.auth0.jwt.JWT;
import com.wakaka.framework.jwt.create.JwtDemo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Crysmart
 * @date 2020/7/22 16:28
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/login")
    public String login(String id){
        System.out.println("登录中。。。");
        String secret = JwtDemo.createToken("secret", id);
        System.out.println("登录完成");
        return secret;
    }

    @GetMapping("/getUser")
    public String getUser(String id,String token){
        String s = JWT.decode(token).getAudience().get(0);
        Date expiresAt = JWT.decode(token).getExpiresAt();
        if (s.equals(id)) {
            return "登录成功";
        }
        return "登录失败";
    }
}
