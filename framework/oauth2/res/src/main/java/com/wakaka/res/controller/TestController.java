package com.wakaka.res.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Crysmart
 * @date 2020/12/18 14:19
 */
@RestController
public class TestController {

    @GetMapping("/getAAA")
    public String getAAA(){
        return "可访问";
    }
}
