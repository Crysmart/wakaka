package com.service.demo;

import com.service.myselfrule.MySelfRibbon;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
//对eureka的name服务使用自定义的负载均衡策略
@RibbonClient(name = "SERVICE-PROVIDER",configuration = MySelfRibbon.class)
public class ServiceCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCustomerApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(RestTemplate restTemplate){
        return args -> {
            System.out.println("启动成功");
            System.out.println("restTemplate = " + restTemplate);
        };
    }
}
