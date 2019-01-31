package com.example.springbootfreemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/1/31
 * @time: 18:00
 * @modifier:
 * @since:
 */
@Controller
public class IndexController {

    @GetMapping(name = "hello")
    public String hello(Model model, ModelMap modelMap) {
        modelMap.addAttribute("name", "freemarker2");
//        model.addAttribute("name", "freemarker");
        return "index";
    }
}
