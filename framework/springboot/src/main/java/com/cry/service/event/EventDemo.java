package com.cry.service.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class EventDemo {

    @EventListener
    public void func(MyEvent myEvent){
        System.out.println("被listener中");
        System.out.println(myEvent.getMsg());
    }
}
