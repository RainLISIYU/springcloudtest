package com.example.springcloudclient.jdbctest.controller;


import com.example.springcloudclient.jdbctest.model.auto.User;
import com.example.springcloudclient.jdbctest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-10-08
 */
@RestController
@RequestMapping("//user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/test")
    public List<User> Test(){
        return userService.getAllUsers();
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("/pageTest")
    public List<User> pageTest(){
        return userService.pagelist(2,2);
    }

    /**
     * 插入测试
     * @return
     */
    @RequestMapping("/insertTest")
    public int insertTest(){
        User user = new User();
        user.setId(5);
        user.setUsername("lsy5");
        user.setPassword("32823");
        return userService.insertUser(user);
    }

    /**
     * 更新测试
     * @return
     */
    @RequestMapping("/updateTest")
    public int updateTest(){
        User user = new User();
        user.setId(5);
        user.setUsername("lsy4s");
        user.setPassword("sdfjdsf");
        user.setVersion(1);
        return userService.updateUser(user);
    }



}
