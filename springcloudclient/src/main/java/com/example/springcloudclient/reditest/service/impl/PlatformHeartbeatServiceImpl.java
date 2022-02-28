package com.example.springcloudclient.reditest.service.impl;

import com.example.springcloudclient.reditest.mapper.auto.PlatformHeartbeatMapper;
import com.example.springcloudclient.reditest.model.auto.PlatformHeartbeat;
import com.example.springcloudclient.reditest.service.PlatformHeartbeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SpringCloudTest
 * 心跳service实现
 *
 * @author : Mr.L
 * @date : 2022-02-28 18:18
 **/
@Service
public class PlatformHeartbeatServiceImpl implements PlatformHeartbeatService {

    @Autowired
    private PlatformHeartbeatMapper platformHeartbeatMapper;

    @Override
    public int insertPlatformHeartbeat(PlatformHeartbeat platformHeartbeat) {

        boolean flag = platformHeartbeatMapper.save(platformHeartbeat);
        return flag ? 1 : 0;

    }

}
