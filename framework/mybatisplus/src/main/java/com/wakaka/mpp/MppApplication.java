package com.wakaka.mpp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Crysmart
 * @date 2020/12/25 14:28
 */
@SpringBootApplication
@MapperScan("com.wakaka.mpp.mapper")
public class MppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MppApplication.class,args);
    }
}
