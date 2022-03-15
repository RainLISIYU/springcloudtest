package com.example.springcloudclientthree.fegintest.controller;

import com.example.springcloudclientthree.fegintest.server.FeginTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * SpringCloudTest
 * 通过fegin请求其他服务接口
 *
 * @author : Mr.L
 * @date : 2022-03-09 15:55
 **/
@RestController
@RequestMapping("/feignTest")
public class RequestByFegin{

    @Autowired
    private FeginTest feignTest;

    @RequestMapping("/getRequest")
    public String savePlatformHeart(@RequestParam Map<String, Object> parameters) {
        return feignTest.savePlatformHeart(parameters);
    }
}
