package com.wakaka.framework.spring4.mvc.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.io.IOException;

@Configuration
public class BeanProFile {

    /**
     * 使用依赖于Servlet 3.0 的文件解析器
     * 内部配置通过DispatcherServlet初始化器来进行设置
     * spring 3.1添加
     *
     * @see MyAddWebInit,MySetMvcConfig
     * @return 文件解析器使用的实现
     */
    @Bean
    public MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }

    /**
     * 使用非Servlet 3.0支持的文件解析器，通常适用于老版本
     * @return 文件解析器使用的实现
     * @throws IOException 抛出异常
     */
    @Bean
    public MultipartResolver multipartResolverr(@RequestPart) throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setUploadTempDir(new FileSystemResource("/tmp/spittr/uploads"));//路径
        multipartResolver.setMaxUploadSize(2097152);//最大上传2MB
        multipartResolver.setMaxInMemorySize(0);//最大内存占用0
        return multipartResolver;
    }
}
