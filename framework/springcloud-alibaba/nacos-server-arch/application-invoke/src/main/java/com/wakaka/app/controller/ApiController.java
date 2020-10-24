package com.wakaka.app.controller;

import com.wakaka.server1.service.IService1Service;
import com.wakaka.server2.service.IService2Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Crysmart
 * @date 2020/10/23 14:41
 */
@RestController
public class ApiController {

    @org.apache.dubbo.config.annotation.Reference
    IService1Service iService1Service;
    @org.apache.dubbo.config.annotation.Reference
    IService2Service iService2Service;

    @RequestMapping("/getAll")
    public String getAll(){
        String server1 = iService1Service.getServer();
        String server2 = iService2Service.getServer();
        System.out.printf("Server1={%s},Server2={%s}",server1,server2);
        System.out.printf("Server1={%s}",server1);
        StringBuilder sb = new StringBuilder();
        sb.append(server1);
        sb.append(server2);
        return sb.toString();
        //return iService2Service.getServer();
    }
}
