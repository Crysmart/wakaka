package com.wakaka.auth.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Crysmart
 * @date 2020/12/17 15:42
 */
public class WebConfig implements WebMvcConfigurer {

    /**
     * 默认url根跳转路径，为spring security提供
     * spring boot 好像可以不用配置
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/").setViewName("redirect:/login");
    }
}
