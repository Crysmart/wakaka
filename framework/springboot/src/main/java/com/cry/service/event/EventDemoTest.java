package com.cry.service.event;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class EventDemoTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void func(){
        applicationContext.publishEvent(new MyEvent(this,"msg"));
    }
}
