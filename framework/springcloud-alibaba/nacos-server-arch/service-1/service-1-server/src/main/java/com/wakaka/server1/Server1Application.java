package com.wakaka.server1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Crysmart
 * @date 2020/10/23 11:34
 */
@SpringBootApplication
@EnableDiscoveryClient//启用nacos注册发现
@MapperScan("com.wakaka.server1.mapper")
public class Server1Application {
    public static void main(String[] args) {
        SpringApplication.run(Server1Application.class,args);
    }
}
