package com.wakaka.springcloud.alibaba;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author Crysmart
 * @date 2020/9/22 19:26
 */
@SpringBootApplication
public class NacosServerApplication {
    public static void main(String[] args) throws Exception {
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

    public static void withNamespace() throws NacosException {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR,"localhost:8848");
        properties.put(PropertyKeyConst.NAMESPACE,"bc8f6500-589a-4a46-a579-a5880a383300");
        ConfigService configService = NacosFactory.createConfigService(properties);
        String default_group = configService.getConfig("test.yml", "DEFAULT_GROUP", 5000);
        System.out.println(default_group);
    }

    public static void withListener() throws Exception {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR,"localhost:8848");
        properties.put(PropertyKeyConst.NAMESPACE,"bc8f6500-589a-4a46-a579-a5880a383300");
        ConfigService configService = NacosFactory.createConfigService(properties);
        configService.addListener("test.yml", "DEFAULT_GROUP", new Listener() {
            @Override
            public Executor getExecutor() {
                System.out.println("getExecutor");
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println(configInfo);
            }
        });

        while (true){
            Thread.sleep(1111l);
            System.out.println("==");
        }
    }

}
