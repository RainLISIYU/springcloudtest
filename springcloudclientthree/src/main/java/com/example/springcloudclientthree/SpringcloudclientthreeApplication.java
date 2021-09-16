package com.example.springcloudclientthree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringcloudclientthreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudclientthreeApplication.class, args);
    }

}
