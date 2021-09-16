package com.example.springcloudclientthree.liang.service;

import org.springframework.stereotype.Service;

/**
 * SpringCloudTest
 * 生成者3service
 *
 * @author : Mr.L
 * @date : 2021-04-16 15:13
 **/
@Service
public class ProviderThreeService {

    public String run(String name){
        return "this is provider three, " + name;
    }

}
