package com.example.springbootmybatis.controller;

import com.example.springbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "listUser")
    public Object listUser() {
        return userService.listUser();
    }
}
