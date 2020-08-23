package com.service.feigndemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaSecondary2Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaSecondary2Application.class, args);
    }

}
