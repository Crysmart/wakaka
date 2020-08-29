package com.service.demo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Crysmart
 * @date 2020/8/18 11:28
 */
@Configuration
public class InitializationConfig {

    @Bean
    @LoadBalanced//开启Ribbon负载均衡
    public RestTemplate InitRestTemplate(){
        return new RestTemplate();
    }
}
