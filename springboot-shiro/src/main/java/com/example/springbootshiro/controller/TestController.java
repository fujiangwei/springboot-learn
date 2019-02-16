package com.example.springbootshiro.controller;

import com.example.springbootshiro.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private SysUserService userService;

    @RequestMapping(value = "test")
    @ResponseBody
    public Object test() {
        return userService.getByUserName("root");
    }
}
