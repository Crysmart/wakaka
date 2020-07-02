package com.platform.flowable;

import com.platform.flowable.conf.AppDispatcherServletConfiguration;
import com.platform.flowable.conf.ApplicationConfiguration;
import org.flowable.ui.modeler.conf.DatabaseConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

//启用全局异常拦截器
@Import(value={
        // 引入修改的配置
        ApplicationConfiguration.class,
        AppDispatcherServletConfiguration.class,
        // 引入 DatabaseConfiguration 表更新转换
        DatabaseConfiguration.class})
// Eureka 客户端
//@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.platform.flowable.*"})
@MapperScan("com.platform.flowable.dal.*")
// 移除 Security 自动配置
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class DesignerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DesignerApplication.class, args);
    }
}