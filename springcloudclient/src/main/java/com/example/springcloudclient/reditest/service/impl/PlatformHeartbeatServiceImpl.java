package com.example.springcloudclient.reditest.service.impl;

import com.example.springcloudclient.reditest.mapper.auto.PlatformHeartbeatMapper;
import com.example.springcloudclient.reditest.model.auto.PlatformHeartbeat;
import com.example.springcloudclient.reditest.service.PlatformHeartbeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int insertPlatformHeartbeat(PlatformHeartbeat platformHeartbeat) {

        Object heartBeat = redisTemplate.opsForValue().get(platformHeartbeat.getPlatformId());
        int flag = 0;
        if (heartBeat != null){
            flag = platformHeartbeatMapper.updateById(platformHeartbeat);
        }else{
            flag = platformHeartbeatMapper.insert(platformHeartbeat);
        }
        redisTemplate.opsForValue().set(platformHeartbeat.getPlatformId(), platformHeartbeat);
        return flag;

    }

    @Override
    public Integer getMaxBeatId() {
        Integer maxId = platformHeartbeatMapper.getMaxBeatId();
        maxId = maxId == null ? 0 : maxId;
        return maxId;
    }

}
