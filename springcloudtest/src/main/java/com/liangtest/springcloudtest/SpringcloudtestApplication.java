package com.liangtest.springcloudtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author liangsiyu01
 */
@SpringBootApplication
@EnableEurekaServer
public class SpringcloudtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudtestApplication.class, args);
    }

}
