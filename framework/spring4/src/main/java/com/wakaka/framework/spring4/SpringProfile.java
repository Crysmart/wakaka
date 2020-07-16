package com.wakaka.framework.spring4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
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

    /*@Bean
    public JavaBean init(){
        //PropertySource + Environment 则可以获取properties中的内容
        String username = environment.getProperty("username");
        System.out.println("username = " + username);
        return new JavaBean();
    }*/

    public static void main(String[] args) {
        //spring注解注入bean
        new AnnotationConfigApplicationContext(SpringProfile.class);
    }

    /**
     * 指定开发的模型
     */
    @Profile("dev")
    @Bean
    public void functionDev(){}
    @Profile("prod")
    @Bean
    public void functionProd(){}
}
