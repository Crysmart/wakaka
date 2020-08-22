package com.service.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaSecondary1Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaSecondary1Application.class, args);
    }

}
