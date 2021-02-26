package com.wakaka.mq.config;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Crysmart
 * @date 2021/2/26 16:58
 */
@Configuration
public class MqConfig {

    private final String addr = "127.0.0.1:9876";

    @Bean
    public TransactionMQProducer initTranMqProducer(){
        TransactionMQProducer producer = new TransactionMQProducer("test");
        producer.setNamesrvAddr(addr);
        return producer;
    }

    @Bean
    public DefaultMQPushConsumer initMqConsumer(){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr(addr);
        return consumer;
    }

}
