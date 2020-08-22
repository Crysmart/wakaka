package com.service.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Crysmart
 * @date 2020/8/17 16:42
 */
@SpringBootApplication
@MapperScan("com.service.demo.dao")
@EnableEurekaClient
public class ServiceProvider3Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProvider3Application.class,args);
    }
}
