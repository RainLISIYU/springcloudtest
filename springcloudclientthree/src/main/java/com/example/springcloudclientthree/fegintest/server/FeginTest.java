package com.example.springcloudclientthree.fegintest.server;

import com.example.springcloudclientthree.fegintest.controller.RequestByFegin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * SpringCloudTest
 * fegin接口
 *
 * @author : Mr.L
 * @date : 2022-03-09 15:56
 **/
@FeignClient(name = "service-hi", fallback = RequestByFegin.class)
public interface FeginTest {

    /**
     * 调用外部心跳接口
     * @param parameters
     * @return
     */
    @RequestMapping("/redisTest/savePlatformHeartbeat")
    String savePlatformHeart(@RequestParam Map<String, Object> parameters);

}
