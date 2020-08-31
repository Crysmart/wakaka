package com.service.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Crysmart
 * @date 2020/8/17 16:42
 */
@SpringBootApplication
@MapperScan("com.service.demo.dao")
@EnableEurekaClient
@EnableCircuitBreaker//对hystrixR熔断机制的支持
public class ServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProviderApplication.class,args);
    }
}
