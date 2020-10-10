package com.wakaka.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Crysmart
 * @date 2020/9/22 19:49
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    //非动态获取配置信息，需要重启才能生效
    @Value("${comm.testdev}")
    private boolean testdev;

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private Environment environment;

    @RequestMapping("/get")
    public boolean get() {
        return testdev;
    }

    /**
     * 动态获取
     * @return
     */
    @RequestMapping("/dynamic")
    public Object dynamic() {
        return applicationContext.getEnvironment().getProperty("comm.testdev");
    }

    /**
     * 动态获取
     * @return
     */
    @RequestMapping("/dynamicByEnv")
    public Object dynamicByEnv() {
        return environment.getProperty("comm.testdev");
    }
}