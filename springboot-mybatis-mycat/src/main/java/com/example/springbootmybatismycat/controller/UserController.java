package com.example.springbootmybatismycat.controller;

import com.example.springbootmybatismycat.domain.User;
import com.example.springbootmybatismycat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/1/31
 * @time: 21:58
 * @modifier:
 * @since:
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "listUser")
    public List<User> listUser() {
        return userService.listUser();
    }

    @GetMapping(value = "addUser")
    public String addUser(User user) {
        userService.addUser(user);

        return "ok";
    }
}
