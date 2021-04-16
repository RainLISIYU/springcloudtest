package com.example.springcloudribbon.liang.controller;

import com.example.springcloudribbon.liang.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringCloudTest
 * 消费测试controller
 *
 * @author : Mr.L
 * @date : 2021-04-15 17:48
 **/
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return helloService.hiService(name);
    }

}
