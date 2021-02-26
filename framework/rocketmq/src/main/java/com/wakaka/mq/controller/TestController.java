package com.wakaka.mq.controller;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Crysmart
 * @date 2021/2/25 14:44
 */
@Controller
public class TestController {

    @GetMapping("test")
    public String test() throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("test2");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        try {
            Message msg = new Message("Call_Supplier_Topic",
                    "Call_Supplier_Tag",
                    ("Hello RocketMQ ").getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.sleep(1000);
        }
        producer.shutdown();
        return "^66";
    }
}
