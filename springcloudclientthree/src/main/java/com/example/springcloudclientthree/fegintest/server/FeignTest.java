package com.example.springcloudclientthree.fegintest.server;

import com.example.springcloudclientthree.fegintest.controller.RequestByFegin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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
@Repository
public interface FeignTest {

    /**
     * 调用外部心跳接口
     * @param parameters
     * @return
     */
    @RequestMapping("/redisTest/savePlatformHeartbeat/{page}/{num}")
    String savePlatformHeart(@RequestParam Map<String, Object> parameters);

    /**
     * 请求用户查询接口
     * @return
     */
    @RequestMapping("/user/test/{page}/{num}")
    String getUserList(@PathVariable Integer page, @PathVariable Integer num);

}
