package com.wakaka.getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Crysmart
 * @date 2020/10/23 15:52
 */
@SpringBootApplication
@EnableDiscoveryClient//请求负载均衡
@EnableZuulProxy//网关代理
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }
}
