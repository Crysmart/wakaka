package com.wakaka.zipkinserver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.wakaka.feignapi"})
public class ZipkinServer2Application {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServer2Application.class, args);
    }

}
