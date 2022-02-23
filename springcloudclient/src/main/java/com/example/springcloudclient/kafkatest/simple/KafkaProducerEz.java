package com.example.springcloudclient.kafkatest.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * SpringCloudTest
 * 简单生成者
 *
 * @author : Mr.L
 * @date : 2021-11-01 13:57
 **/
@RestController
public class KafkaProducerEz {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @RequestMapping("/kafka/normal/{message}")
    public void sendMessage1(@PathVariable("message") String normalMessage){
        kafkaTemplate.send("topic1", normalMessage);
        try {
            TimeUnit.MILLISECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send("topic2", "topic2发送消息");
    }
}
