package com.wakaka.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Crysmart
 * @date 2020/9/9 17:59
 */
@RestController
public class ConfigController {
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String port;

    @Override
    public String toString() {
        return "ConfigController{" +
                "applicationName='" + applicationName + '\'' +
                ", port='" + port + '\'' +
                '}';
    }

    @RequestMapping("/config")
    public String getConfig()
    {
        return this.toString();
    }

}
