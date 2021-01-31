package com.wakaka.server2.server;

import com.alibaba.fastjson.JSONObject;
import com.wakaka.server2.entity.DbServer;
import com.wakaka.server2.mapper.DbServerMapper;
import com.wakaka.server2.service.IService2Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Crysmart
 * @date 2020/10/23 11:47
 */
@org.apache.dubbo.config.annotation.Service
public class Service2ServiceImpl implements IService2Service {
    @Resource
    DbServerMapper dbServerMapper;

    @Override
    public String getServer() {
        List<DbServer> dbServers = dbServerMapper.selectList(null);
        return JSONObject.toJSONString(dbServers);
    }
}
