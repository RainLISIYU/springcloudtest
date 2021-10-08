package com.example.springcloudclient.jdbctest.model.auto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2021-10-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("USER")
public class User extends Model {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Integer id;

    @TableField("USERNAME")
    private String username;

    @TableField("PASSWORD")
    private String password;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date create_time;

    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    private Date update_time;

    @Version
    @TableField(value = "VERSION",fill = FieldFill.INSERT)
    private Integer version;


}
