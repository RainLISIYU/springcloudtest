package com.example.springcloudclient.jdbctest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springcloudclient.jdbctest.model.auto.User;
import com.example.springcloudclient.jdbctest.mapper.auto.UserMapper;
import com.example.springcloudclient.jdbctest.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<User> pagelist(int current, int size) {
        //定义page
        Page<User> page = new Page<>(current, size);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> userList = userMapper.selectPage(page, null).getRecords();
        return userList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int insertUser(User user) {
        int num = userMapper.insert(user);
        System.out.println(user.getUsername() + " user insert success "+ num);
        throw new RuntimeException();
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }

}
