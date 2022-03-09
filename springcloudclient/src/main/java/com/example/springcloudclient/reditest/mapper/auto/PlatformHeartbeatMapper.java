package com.example.springcloudclient.reditest.mapper.auto;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springcloudclient.reditest.model.auto.PlatformHeartbeat;
import org.springframework.stereotype.Repository;

/**
 * SpringCloudTest
 * 平台心跳mapper
 *
 * @author : Mr.L
 * @date : 2022-02-28 18:20
 **/
@Repository
public interface PlatformHeartbeatMapper extends BaseMapper<PlatformHeartbeat> {

    /**
     * 报错心跳信息
     * @param platformHeartbeat
     * @return
     */
    int saveHeartBeat(PlatformHeartbeat platformHeartbeat);

    /**
     * 获取最大ID
     * @return
     */
    Integer getMaxBeatId();

}
