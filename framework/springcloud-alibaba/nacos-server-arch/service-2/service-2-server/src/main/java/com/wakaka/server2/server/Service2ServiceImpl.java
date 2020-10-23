package com.wakaka.server2.server;

import com.wakaka.server2.service.IService2Service;

/**
 * @author Crysmart
 * @date 2020/10/23 11:47
 */
@org.apache.dubbo.config.annotation.Service
public class Service2ServiceImpl implements IService2Service {
    @Override
    public String getServer() {
        return "getServer2222222";
    }
}
