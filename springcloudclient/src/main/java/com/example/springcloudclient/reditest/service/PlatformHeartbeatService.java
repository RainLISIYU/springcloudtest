package com.example.springcloudclient.reditest.service;

import com.example.springcloudclient.reditest.model.auto.PlatformHeartbeat;

/**
 * SpringCloudTest
 * 平台心跳service
 *
 * @author : Mr.L
 * @date : 2022-02-28 18:17
 **/
public interface PlatformHeartbeatService {

    /**
     * 插入心跳指令
     * @param platformHeartbeat
     * @return
     */
    int insertPlatformHeartbeat(PlatformHeartbeat platformHeartbeat);

}
