package com.example.springcloudribbon.liang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * SpringCloudTest
 * 消费测试service
 *
 * @author : Mr.L
 * @date : 2021-04-15 17:49
 **/
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    public String hiService(String name){
        return restTemplate.getForObject("http://localhost:8763/hi?name=" + name , String.class);
    }

}
