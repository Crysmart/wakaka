package com.cry;

import com.cry.controller.JobController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author Crysmart
 * @date 2020/7/21 14:46
 */
@SpringBootApplication
public class SbApplication implements ApplicationContextAware {


    public static void main(String[] args){
        SpringApplication.run(SbApplication.class,args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Object sbApplication = applicationContext.getBean(JobController.class);
        System.out.println(sbApplication);
    }
}
