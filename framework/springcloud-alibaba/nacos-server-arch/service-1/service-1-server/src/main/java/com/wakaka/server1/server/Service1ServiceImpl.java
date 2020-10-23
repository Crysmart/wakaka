package com.wakaka.server1.server;

import com.wakaka.server1.service.IService1Service;

/**
 * @author Crysmart
 * @date 2020/10/23 11:44
 */
@org.apache.dubbo.config.annotation.Service
public class Service1ServiceImpl implements IService1Service {
    @Override
    public String getServer() {
        return "getServer1111111";
    }
}
