package com.example.springbootthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/17
 * @time: 12:03
 * @modifier:
 * @since:
 */
@Controller
public class IndexController {

    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }
}
