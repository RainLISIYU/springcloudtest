package com.example.springcloudclient.jdbctest.model.auto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

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


}
