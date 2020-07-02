package com.szb.platform.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Wang.hm
 * @date 2019年12月16日 14点29分
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.szb.platform.workflow.dal.persistence")
public class WorkflowApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkflowApplication.class, args);
    }
}
