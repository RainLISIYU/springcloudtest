package com.example.springcloudclient.reditest.controller;

import com.example.springcloudclient.reditest.model.auto.PlatformHeartbeat;
import com.example.springcloudclient.reditest.service.PlatformHeartbeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

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
        return "Insert Success !" + redisTemplate.opsForValue().get("key1");
    }



    @RequestMapping("/savePlatformHeartbeat")
    public String savePlatformHeartbeat(@RequestParam Map<String, Object> parameters){
        String platformId = String.valueOf(parameters.get("platformId"));
        String linkStatus = String.valueOf(parameters.get("linkStatus"));
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HHmmss");
        String currentTime = format.format(new Date());
        PlatformHeartbeat platformHeartbeat = null;
        Object obj = redisTemplate.opsForValue().get(platformId);
        if (obj == null){
            platformHeartbeat = new PlatformHeartbeat();
            int id = getIdFromRedis(platformId);
            platformHeartbeat.setId(id);
            platformHeartbeat.setPlatformId(platformId);
            platformHeartbeat.setLinkStatus(linkStatus);
            platformHeartbeat.setDataTime(currentTime);
        }else{
            platformHeartbeat = (PlatformHeartbeat) obj;
            platformHeartbeat.setLinkStatus(linkStatus);
            platformHeartbeat.setDataTime(currentTime);
        }
        int num = platformHeartbeatService.insertPlatformHeartbeat(platformHeartbeat);
        return platformId + " 心跳检测成功 " + num;
    }

    @RequestMapping("/getPlatformHeartbeat")
    public PlatformHeartbeat getPlatformHeartbeat(@RequestParam String platformId){
        PlatformHeartbeat platformHeartbeat = (PlatformHeartbeat) redisTemplate.opsForValue().get(platformId);
        return platformHeartbeat;
    }

    @RequestMapping("/syncPlatformRedis")
    public String syncPlatformRedis(){
        int size = platformHeartbeatService.syncPlatformRedis();
        return size + "项数据同步成功";
    }

    @RequestMapping("/getAllPlatHeart")
    public List<PlatformHeartbeat> getAllPlatHeart(){
        List<PlatformHeartbeat> platformHeartbeatList;
        Object obj = redisTemplate.opsForValue().get("platHeartList");
        if (obj != null){
            platformHeartbeatList = (List<PlatformHeartbeat>) obj;
        }else{
            platformHeartbeatList = platformHeartbeatService.getAllPlatHeart();
            redisTemplate.opsForValue().set("platHeartList", platformHeartbeatList);
        }
        return platformHeartbeatList;
    }

    @RequestMapping("/cleanPlatHeart")
    public Boolean cleanPlatHeart(){
        try{
            redisTemplate.delete("platHeartList");
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private int getIdFromRedis(String platformId){
        Object object = redisTemplate.opsForValue().get("platform_heartbeat_id");
        if (object == null){
            Integer maxId = platformHeartbeatService.getMaxBeatId();
            redisTemplate.opsForValue().set("platform_heartbeat_id", maxId);
            object = maxId+1;
        }
        redisTemplate.opsForValue().increment("platform_heartbeat_id");
        return (Integer) object;
    }

    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(8);
        ThreadLocal threadLocal = new ThreadLocal();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,4,2L, TimeUnit.SECONDS,
                queue, new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 18; i++){
            threadPoolExecutor.execute(()->{
                System.out.println("thread " + Thread.currentThread().getName() + " is running!");
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("线程队列长度：" + queue.size());
        }
        threadPoolExecutor.shutdown();
    }

}
