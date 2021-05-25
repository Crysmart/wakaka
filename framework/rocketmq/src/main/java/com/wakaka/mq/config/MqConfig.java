package com.wakaka.mq.config;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Crysmart
 * @date 2021/2/26 16:58
 */
@Configuration
public class MqConfig {

    private final String addr = "192.168.6.193:9876";

    @Bean
    public TransactionMQProducer initTranMqProducer(){
        TransactionMQProducer producer = new TransactionMQProducer("tranMq_ProducerGroup");
        producer.setNamesrvAddr(addr);
        producer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                System.out.println("---半消息伊始---");
                System.out.println("消息Id：" + msg.getTransactionId());
                return LocalTransactionState.COMMIT_MESSAGE;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                System.out.println("---消息回查---");
                System.out.println("消息Id：" + msg.getTransactionId());
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });
        return producer;
    }

    @Bean
    public DefaultMQPushConsumer initMqConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("tranMq_ConsumerGroup");
        consumer.setNamesrvAddr(addr);
        consumer.subscribe("tranTopic","tranTag");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println("---消息消费者---");
                    System.out.println("消息Id：" + msg.getMsgId());
                }
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        consumer.start();
        return consumer;
    }
}
