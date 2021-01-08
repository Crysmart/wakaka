package com.wakaka.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Crysmart
 * @date 2020/12/17 14:37
 */

@SpringBootApplication
public class AuthApplication {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        SpringApplication.run(AuthApplication.class,args);
    }
}
