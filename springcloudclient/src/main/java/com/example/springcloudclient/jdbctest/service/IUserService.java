package com.example.springcloudclient.jdbctest.service;

import com.example.springcloudclient.jdbctest.model.auto.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-10-08
 */
public interface IUserService extends IService<User> {

    /**
     * 获取所有用户
     * @return
     */
    List<User> getAllUsers();

}
