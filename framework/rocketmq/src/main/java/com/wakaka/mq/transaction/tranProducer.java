package com.wakaka.mq.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;

/**
 * @author Crysmart
 * @date 2021/2/26 16:24
 */
public class tranProducer {
    private static int count;
    public static void main(String[] args) throws Exception {
        TransactionMQProducer producer = new TransactionMQProducer("testgroup");
        producer.setNamesrvAddr("192.168.6.193:9876");
        producer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                System.out.println(msg);
                return LocalTransactionState.COMMIT_MESSAGE;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                count = count++;
                System.out.println(count);
                System.err.println(msg);
                return LocalTransactionState.UNKNOW;
            }
        });
        producer.start();
        for (int i = 0; i < 10; i++) {
            Message message = new Message("test", "testtags", "666".getBytes(StandardCharsets.UTF_8));
            producer.sendMessageInTransaction(message,null);
        }
        producer.shutdown();
    }
}
