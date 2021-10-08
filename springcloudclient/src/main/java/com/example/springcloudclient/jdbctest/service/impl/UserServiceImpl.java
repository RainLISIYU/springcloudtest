package com.example.springcloudclient.jdbctest.service.impl;

import com.example.springcloudclient.jdbctest.model.auto.User;
import com.example.springcloudclient.jdbctest.mapper.auto.UserMapper;
import com.example.springcloudclient.jdbctest.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-10-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.findAllUser();
    }

}
