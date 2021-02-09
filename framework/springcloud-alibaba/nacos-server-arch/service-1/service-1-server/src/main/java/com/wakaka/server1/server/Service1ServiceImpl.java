package com.wakaka.server1.server;

import com.alibaba.fastjson.JSONObject;
import com.wakaka.server1.entity.DbServer;
import com.wakaka.server1.mapper.DbServerMapper;
import com.wakaka.server1.service.IService1Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Crysmart
 * @date 2020/10/23 11:44
 */
@org.apache.dubbo.config.annotation.Service
public class Service1ServiceImpl implements IService1Service {

    @Resource
    DbServerMapper dbServerMapper;

    @Override
    public String getServer() {
        List<DbServer> dbServers = dbServerMapper.selectList(null);
        return JSONObject.toJSONString(dbServers);
    }

    @Override
    public String insServer() {
        DbServer dbServer = new DbServer();
        dbServer.setName("玛卡巴卡服务①");
        dbServerMapper.insert(dbServer);
        return "玛卡巴卡①成功";
    }
}
