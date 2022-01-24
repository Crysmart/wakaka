package com.cry.service.job.config;

import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {

    @Bean
    public StdSchedulerFactory initStdSchedulerFactory(){
        return new StdSchedulerFactory();
    }

}
