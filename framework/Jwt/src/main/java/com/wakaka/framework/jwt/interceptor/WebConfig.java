package com.wakaka.framework.jwt.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Crysmart
 * @date 2020/7/22 16:06
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    public WebConfig(){
        System.out.println("WebConfig load...");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/test/**")
                .excludePathPatterns("/error");
    }
}
