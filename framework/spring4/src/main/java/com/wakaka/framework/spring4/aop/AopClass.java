package com.wakaka.framework.spring4.aop;

import org.springframework.stereotype.Component;

/**
 * @author Crysmart
 * @date 2020/7/1 16:52
 */
@Component
public class AopClass implements AopInterface {
    public void aopMethod() {
        System.out.println("这是一个Aop method");
    }
}
