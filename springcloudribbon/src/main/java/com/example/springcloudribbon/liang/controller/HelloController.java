package com.example.springcloudribbon.liang.controller;

import com.example.springcloudribbon.liang.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name){
        ServiceInstance choose = loadBalancerClient.choose("service-hi");
        System.out.println(choose.getPort());
        return helloService.hiService(name);
    }

}
