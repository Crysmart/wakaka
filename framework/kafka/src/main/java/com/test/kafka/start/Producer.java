package com.test.kafka.start;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import java.util.Random;

/**
 * @author Crysmart
 * @date 2020/8/27 15:38
 */
@RestController
public class Producer {
    private final KafkaTemplate<Object, Object> template;

    @Autowired
    public Producer(KafkaTemplate<Object, Object> template) {
        this.template = template;
    }

    @PostMapping(path = "/send/foo/{what}")
    public void sendFoo(@PathVariable String what) {
        this.template.send("test", what);
        this.template.send("test-1-1", what);
        this.template.send("test-2-1", what);
    }

    /*@KafkaListener(topicPattern = "test-1-1")
    public void function1(ConsumerRecord<?, ?> record){
        System.out.println(record.key() + " {test-1-*}= " + record.value());
    }*/
    @KafkaListener(topicPattern = "test-1.*")
    public void function3(ConsumerRecord<?, ?> record){
        System.out.println(record.key() + " {test-1.*}= " + record.value());
    }

    @KafkaListener(topicPattern = "test-2.*")
    public void function2(ConsumerRecord<?, ?> record){
        System.out.println(record.key() + " {test-2-*}= " + record.value());
    }

    @KafkaListener(topicPattern = "test")
    public void function1(ConsumerRecord<?, ?> record){
        System.out.println(record.key() + " {test}= " + record.value());
    }

    public static void main(String[] args) throws InterruptedException {
        Properties p = new Properties();
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.3.208:9092");//kafka地址，多个地址用逗号分割
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(p);

        try {
            while (true) {
                String msg = "Hello," + new Random().nextInt(100);
                ProducerRecord<String, String> record = new ProducerRecord<String, String>("test", msg);
                kafkaProducer.send(record);
                System.out.println("消息发送成功:" + msg);
                Thread.sleep(500);
            }
        } finally {
            kafkaProducer.close();
        }
    }
}
