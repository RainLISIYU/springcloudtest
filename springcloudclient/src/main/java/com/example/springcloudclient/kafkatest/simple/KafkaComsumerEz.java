package com.example.springcloudclient.kafkatest.simple;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * SpringCloudTest
 * 简单消费者
 *
 * @author : Mr.L
 * @date : 2021-11-01 14:00
 **/
@Component
public class KafkaComsumerEz {

    @KafkaListener(groupId = "test", topics = {"topic1","topic2","test3"})
    public void onMessage1(ConsumerRecord<?, ?> record){
        System.out.println("简单消费者1："+record.topic() + "-" + record.partition() + "-" + record.value());
    }

    @KafkaListener(groupId = "test", topics = {"test3"})
    public void onMessage2(ConsumerRecord<?, ?> record){
        System.out.println("简单消费者2："+record.topic() + "-" + record.partition() + "-" + record.value());
    }
}
