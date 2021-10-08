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

    /**
     * 分页查询
     * @param current 当前页
     * @param size 每页条数
     * @return
     */
    List<User> pagelist(int current, int size);

    /**
     * 插入
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 更新
     * @param user
     * @return
     */
    int updateUser(User user);

}
