package com.wakaka.framework.jwt.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Crysmart
 * @date 2020/7/22 16:53
 */
public class AuthInterceptor implements HandlerInterceptor {

    public AuthInterceptor(){
        System.out.println("AuthInterceptor load...");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进来le");
        return true;
    }
}
