package com.wakaka.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Crysmart
 * @date 2020/10/23 11:30
 */
@SpringBootApplication
@EnableDiscoveryClient//启用nacos注册发现
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class,args);
    }
}
