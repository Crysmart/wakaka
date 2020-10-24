package com.wakaka.server1;

import com.wakaka.server1.entity.DbServer;
import com.wakaka.server1.mapper.DbServerMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Crysmart
 * @date 2020/10/24 15:25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisPlusConnectionTest {

    @Autowired
    DbServerMapper dbServerMapper;

    @Test
    public void connectionTest(){
        List<DbServer> dbServers = dbServerMapper.selectList(null);
        Assert.assertEquals(1,dbServers.size());
        dbServers.forEach(System.out::println);
    }
}
