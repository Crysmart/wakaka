package com.wakaka.mq.controller;

import com.wakaka.mq.entity.TestEntity;
import com.wakaka.mq.mapper.TestMapper;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.charset.StandardCharsets;

/**
 * @author Crysmart
 * @date 2021/2/26 16:52
 */
@Controller
public class TranTestController {

    @Autowired
    TransactionMQProducer transactionMQProducer;

    @RequestMapping("trantest")
    public void trantest() throws Exception {
        transactionMQProducer.start();

        TransactionSendResult transactionSendResult = transactionMQProducer.sendMessageInTransaction(new Message() {{
            this.setBody("oh my gosh".getBytes(StandardCharsets.UTF_8));
            this.setTopic("tranTopic");
            this.setTags("tranTag");
        }}, null);
        System.out.println("---发送消息方---");
        System.out.println("消息状态：" + transactionSendResult.getLocalTransactionState());
        System.out.println("消息事务Id：" + transactionSendResult.getTransactionId());
        System.out.println("消息Id：" + transactionSendResult.getMsgId());

        Thread.sleep(1000);
        transactionMQProducer.shutdown();
    }
}
