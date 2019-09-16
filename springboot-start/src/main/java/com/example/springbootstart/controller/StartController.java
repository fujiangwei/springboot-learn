package com.example.springbootstart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/1/12
 * @time: 21:38
 * @modifier:
 * @since:
 */
@RestController
@RequestMapping(value = "/start")
public class StartController {

    @RequestMapping(value = "/hello")
//    @ResponseBody
    public String hello() {
        System.out.println("coming in");
        return "hello, Springboot";
    }
}
