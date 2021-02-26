package com.wakaka.mq.ttest;

import com.wakaka.mq.RocketMqApplication;
import com.wakaka.mq.entity.TestEntity;
import com.wakaka.mq.mapper.TestMapper;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;

/**
 * @author Crysmart
 * @date 2021/2/26 17:34
 */
@SpringBootTest(classes = RocketMqApplication.class)
@RunWith(SpringRunner.class)
public class MqTest {

    @Autowired
    TestMapper testMapper;

    @Autowired
    TransactionMQProducer transactionMQProducer;

    @Test
    public void function() throws Exception{
        TestEntity testEntity = new TestEntity();
        testEntity.setName2("123456");
        testEntity.setName("zxc");
        testEntity.setId(111L);
        testMapper.saveAndFlush(testEntity);

        transactionMQProducer.start();

        transactionMQProducer.sendMessageInTransaction(new Message("Call_Supplier_Topic","666".getBytes(StandardCharsets.UTF_8)),testEntity);
    }
}
