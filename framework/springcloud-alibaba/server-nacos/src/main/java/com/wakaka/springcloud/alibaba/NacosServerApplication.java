package com.wakaka.springcloud.alibaba;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Crysmart
 * @date 2020/9/22 19:26
 */
@SpringBootApplication
public class NacosServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosServerApplication.class,args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> {
            ConfigService configService = NacosFactory.createConfigService("127.0.0.1:8848");
            String default_group = configService.getConfig("test.data.id", "DEFAULT_GROUP", 5000);
            System.out.println(default_group);
        };
    }
}
