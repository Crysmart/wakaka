package com.wakaka.framework.spring4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring value
 * @author Crysmart
 */
public class SpringConfig {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-value.xml");
    }
}
