package com.wakaka.framework.spring4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * spring context
 * @author Crysmart
 */
@Configuration
@PropertySource("classpath:application.properties")
public class SpringProfile {

    @Autowired
    private Environment environment;

    @Bean
    public JavaBean init(){
        //PropertySource + Environment 则可以获取properties中的内容
        String username = environment.getProperty("username");
        System.out.println("username = " + username);
        return new JavaBean();
    }

    public static void main(String[] args) {
        System.out.println(1);
        //spring注解注入bean
        //new AnnotationConfigApplicationContext(SpringProfile.class);
    }
}
