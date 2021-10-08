package com.example.springcloudclient.jdbctest.mapper.auto;

import com.example.springcloudclient.jdbctest.model.auto.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2021-10-08
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取所有用户
     * @return
     */
    List<User> findAllUser();

}
