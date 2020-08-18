package com.service.demo.config;

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
    public RestTemplate InitRestTemplate(){
        return new RestTemplate();
    }
}
