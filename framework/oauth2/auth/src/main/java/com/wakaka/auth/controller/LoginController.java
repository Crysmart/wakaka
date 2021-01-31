package com.wakaka.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Crysmart
 * @date 2020/12/17 15:27
 */
@RestController
public class LoginController {

    @PostMapping("/welcome-index")
    public String welcome(){
        return getUsername()+":登录成功";
    }

    /**
     * 获取用户信息
     * @return
     */
    public String getUsername(){
        String username = "登录";
        //获取用户凭证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //用户身份，是UserDetails还是字符串
        Object principal = authentication.getPrincipal();
        if (ObjectUtils.isEmpty(principal)){
            return username;
        }
        if (principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();
        }
        return username;
    }
}
