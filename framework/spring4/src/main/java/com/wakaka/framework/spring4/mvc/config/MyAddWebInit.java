package com.wakaka.framework.spring4.mvc.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

/**
 * 自定义web初始化器
 */
public class MyAddWebInit implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        //添加自定义filter并且设置映射规则
        servletContext.addFilter("myFilter", "xxFilter").addMappingForUrlPatterns(null,false,"/custom/**");
        //添加自定义servlet并设置映射规则
        servletContext.addServlet("myServlet", "xxServet").addMapping("/custom/**");
        //添加自定义Listener
        servletContext.addListener("xxListener");


        //设置文件上传参数配置
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        ServletRegistration.Dynamic appServlet = servletContext.addServlet("appServlet", dispatcherServlet);
        appServlet.addMapping("/");
        //设置上传文件的location，size等参数
        appServlet.setMultipartConfig(new MultipartConfigElement("/temp/upload"));
    }
}
