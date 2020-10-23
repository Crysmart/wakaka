package com.wakaka.server2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Crysmart
 * @date 2020/10/23 11:37
 */
@SpringBootApplication
@EnableDiscoveryClient//启用nacos注册发现
public class Server2Application {
    public static void main(String[] args) {
        SpringApplication.run(Server2Application.class,args);
    }
}
