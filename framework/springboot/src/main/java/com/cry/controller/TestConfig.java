package com.cry.controller;

import com.cry.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConfig {

    @Autowired
    UtilService utilService;

    @GetMapping("/getString")
    public String fucntion(){
        return utilService.stringMockAware();
    }
}
