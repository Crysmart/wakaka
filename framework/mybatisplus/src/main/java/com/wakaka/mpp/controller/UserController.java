package com.wakaka.mpp.controller;

import com.wakaka.mpp.service.IUmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Crysmart
 * @date 2020/12/25 14:48
 */
@RestController
public class UserController {
    @Autowired
    IUmsUserService iUmsUserService;

    @GetMapping("test")
    public void function(){
        iUmsUserService.get();
    }
}
