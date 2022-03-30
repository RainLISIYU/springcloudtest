package com.example.springcloudclient.jdbctest.controller;


import com.example.springcloudclient.jdbctest.model.auto.User;
import com.example.springcloudclient.jdbctest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-10-08
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/test/{page}/{num}")
    public List<User> Test(@PathVariable Integer page, @PathVariable Integer num){
        List<User> userList = userService.pagelist(page, num);
        Optional<User> user = userList.stream().sorted((User u1, User u2) -> u2.getId() - u1.getId()).findFirst();
        Integer id = user.get().getId();
        redisTemplate.opsForValue().set("max_user_id", id);
        return userList;
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
    public Integer insertTest(){
        User user = new User();
        Integer id = (Integer) redisTemplate.opsForValue().get("max_user_id")+1;
        user.setId(id);
        user.setUsername("lsy"+id);
        user.setPassword(new Random(System.nanoTime()).nextInt()+"");
        Integer insertNum = userService.insertUser(user);
        if (insertNum != 0){
            redisTemplate.opsForValue().increment("max_user_id");
        }
        return insertNum;
    }

    /**
     * 更新测试
     * @return
     */
    @RequestMapping("/updateTest")
    public Integer updateTest(){
        User user = new User();
        user.setId(5);
        user.setUsername("lsy4s");
        user.setPassword("sdfjdsf");
        user.setVersion(1);
        return userService.updateUser(user);
    }

    /**
     * 更新测试
     * @return
     */
    @PostMapping("/updateUser")
    public Integer updateTest(@RequestBody List<User> users){
        User user = users.get(0);
        Integer id = user.getId();
        String name = user.getUsername();
        return userService.updateUser(user);
    }


}
