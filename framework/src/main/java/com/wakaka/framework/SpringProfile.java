package com.wakaka.framework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlReaderContext;
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

    @Bean
    public JavaBean init(){
        //PropertySource + Environment 则可以获取properties中的内容
        String name = environment.getProperty("name");
        System.out.println("name = " + name);
        return new JavaBean();
    }

    public static void main(String[] args) {
        //spring注解注入bean
        new AnnotationConfigApplicationContext(SpringProfile.class);
    }
}
