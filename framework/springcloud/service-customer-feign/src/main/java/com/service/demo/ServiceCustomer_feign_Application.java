package com.service.demo;

import com.service.myselfrule.MySelfRibbon;
import dao.service.DeptClientServiceFallbackFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {
        "dao",
        "com.service.demo"
})
@EnableEurekaClient
//对eureka的name服务使用自定义的负载均衡策略
//@RibbonClient(name = "SERVICE-PROVIDER",configuration = MySelfRibbon.class)
@EnableFeignClients(basePackages = "dao")//启用feign服务
public class ServiceCustomer_feign_Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCustomer_feign_Application.class, args);
    }

    @Bean
    public CommandLineRunner init(DeptClientServiceFallbackFactory deptClientServiceFallbackFactory){
        return args -> {
            System.out.println("启动成功");
            System.out.println("deptClientServiceFallbackFactory = " + deptClientServiceFallbackFactory);
        };
    }
}
