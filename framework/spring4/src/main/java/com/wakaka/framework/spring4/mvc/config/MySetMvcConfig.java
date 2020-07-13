package com.wakaka.framework.spring4.mvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * @author Crysmart
 * @date 2020/7/8 17:22
 */
public class MySetMvcConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        //注册文件上传配置参数
        registration.setMultipartConfig(new MultipartConfigElement(
                "temp/uploads",//文件存放磁盘路径
                2097152, //最大文件size 2MB
                4194304, //最大请求 4MB
                0));
    }
}
