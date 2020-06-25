package com.wakaka.framework.spring4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring value
 * @author Crysmart
 */
public class SpringConfig {

    public static void main(String[] args) {
        //从xml中获取配置环境
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-value.xml");
        JavaBean bean = context.getBean(JavaBean.class);
        System.out.println("bean.toString() = " + bean.toString());
    }
}
