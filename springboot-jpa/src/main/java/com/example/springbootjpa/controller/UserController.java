package com.example.springbootjpa.controller;

import com.example.springbootjpa.domain.User;
import com.example.springbootjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/2/17
 * @time: 14:03
 * @modifier:
 * @since:
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "listUser")
    @ResponseBody
    public List<User> listUser() {
        User byUserId = userService.findByUserId(1);
        User fjw = userService.findUser("hzq");
        return userService.listUser();
    }
}
