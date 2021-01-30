package com.wakaka.sckill_redis.controller;

import com.wakaka.sckill_redis.entity.User;
import com.wakaka.sckill_redis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Crysmart
 * @date 2021/1/29 16:10
 */
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("test")
    public List<User> fucntion(){
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        User save = userRepository.save(user);
        return userRepository.findAll();
    }
}
