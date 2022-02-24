package com.example.springcloudclient.reditest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringCloudTest
 * Redis测试类
 *
 * @author : Mr.L
 * @date : 2022-02-24 18:03
 **/
@RestController
@RequestMapping("/redisTest")
public class RedisUserController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/insertTest/{message}")
    public String insertRedisTest(@PathVariable String message){
        redisTemplate.opsForValue().set("key1", "test1");
        System.out.println(redisTemplate.opsForValue().get("key1"));
        System.out.println(redisTemplate.opsForValue().increment("key1"));
        return "Insert Success !" + redisTemplate.opsForValue().get("key1");
    }

}
