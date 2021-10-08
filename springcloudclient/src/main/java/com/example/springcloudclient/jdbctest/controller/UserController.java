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

    @RequestMapping("/test")
    public List<User> Test(){
        return userService.getAllUsers();
    }

}
