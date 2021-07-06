package com.wakaka.zipkinserver2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("server2")
    public String fucntion(){
        return "server2";
    }
}
