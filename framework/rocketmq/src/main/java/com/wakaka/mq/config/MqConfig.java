package com.wakaka.mq.config;

import com.wakaka.mq.listener.TranListener;
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

    private final String addr = "192.168.6.193:9876";

    @Bean
    public TransactionMQProducer initTranMqProducer(){
        TransactionMQProducer producer = new TransactionMQProducer("test2");
        producer.setNamesrvAddr(addr);
        producer.setTransactionListener(new TranListener());
        return producer;
    }

    @Bean
    public DefaultMQPushConsumer initMqConsumer(){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test2");
        consumer.setNamesrvAddr(addr);
        return consumer;
    }

}
