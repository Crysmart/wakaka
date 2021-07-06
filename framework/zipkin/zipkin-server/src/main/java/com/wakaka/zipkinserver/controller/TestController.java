package com.wakaka.zipkinserver.controller;

import com.wakaka.feignapi.FeignTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    FeignTestService feignTestService;

    @GetMapping("test")
    public String fucntion(){
        return "success";
    }

    @GetMapping("testfeign")
    public String testfeign(){
        return feignTestService.function();
    }
}
