package com.example.springcloudclient.reditest.controller;

import com.example.springcloudclient.reditest.model.auto.PlatformHeartbeat;
import com.example.springcloudclient.reditest.service.PlatformHeartbeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private PlatformHeartbeatService platformHeartbeatService;

    @GetMapping("/insertTest/{message}")
    public String insertRedisTest(@PathVariable String message){
        redisTemplate.opsForValue().set("key1", "12342");
        Map<String, Object> map = new HashMap<>();
        map.put("username",message);
        map.put("userRole","admin");
        System.out.println(redisTemplate.opsForValue().get("key1"));
        //System.out.println(redisTemplate.opsForValue().increment("key1"));
        return "Insert Success !" + redisTemplate.opsForValue().get("key1");
    }

    @RequestMapping("/savePlatformHeartbeat")
    public String savePlatformHeartbeat(@RequestParam Map<String, Object> parameters){
        String platformId = String.valueOf(parameters.get("platformId"));
        String linkStatus = String.valueOf(parameters.get("linkStatus"));
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HHmmss");
        String currentTime = String.valueOf(format.format(new Date()));
        PlatformHeartbeat platformHeartbeat = new PlatformHeartbeat();
        platformHeartbeat.setId(1);
        platformHeartbeat.setPlatformId(platformId);
        platformHeartbeat.setLinkStatus(linkStatus);
        platformHeartbeat.setDataTime(currentTime);
        redisTemplate.opsForValue().set(platformId, platformHeartbeat);
        int num = platformHeartbeatService.insertPlatformHeartbeat(platformHeartbeat);
        return platformId + " 心跳检测成功 " + num;
    }

}
