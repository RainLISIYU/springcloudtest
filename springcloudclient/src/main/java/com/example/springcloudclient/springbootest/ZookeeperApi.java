package com.example.springcloudclient.springbootest;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description:zookeeper基本功能
 * @USER: 梁思禹
 * @DATE: 2022/4/23
 */
@Slf4j
@Component
public class ZookeeperApi {

    @Autowired
    private ZooKeeper zkClient;

    /**
     * 判断节点是否存在
     * @param path
     * @param needWatch
     * @return
     */
    public Stat exists(String path, boolean needWatch){
        try {
            return zkClient.exists(path, needWatch);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getData(String path){
        try {
            byte[] bytes = zkClient.getData(path, true, new Stat());
            return new String(bytes);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 初始化方法
     */
    @PostConstruct
    public void init(){
        String path = "/app";
        Stat stat = exists(path, true);
        log.info("判断/app是否存在："+stat.toString());
        log.info("获取节点值："+getData(path));
    }

}
