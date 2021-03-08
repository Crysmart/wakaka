package com.wakaka.springsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Crysmart
 * @date 2021/3/8 17:12
 */
@RestController
public class TestController {

    @RequestMapping("test")
    public String test(){
        return "aaa";
    }
}
