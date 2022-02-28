package com.example.springcloudclient.reditest.model.auto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SpringCloudTest
 * 企业心跳实体类
 *
 * @author : Mr.L
 * @date : 2022-02-28 16:51
 **/
@Data
@EqualsAndHashCode(callSuper =  true)
@TableName("platform_heart")
public class PlatformHeartbeat extends Model {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    public int id;

    @TableField("platform_id")
    public String platformId;

    @TableField("link_status")
    public String linkStatus;

    @TableField("data_time")
    public String dataTime;

}
