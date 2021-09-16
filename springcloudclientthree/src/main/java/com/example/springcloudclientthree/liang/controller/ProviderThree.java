package com.example.springcloudclientthree.liang.controller;

import com.example.springcloudclientthree.liang.service.ProviderThreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringCloudTest
 * 生成者3controller
 *
 * @author : Mr.L
 * @date : 2021-04-16 15:12
 **/
@RestController
public class ProviderThree {

    @Autowired
    ProviderThreeService providerThreeService;

    @RequestMapping("/hi")
    public String run(@RequestParam String name){

        return providerThreeService.run(name);

    }

}
