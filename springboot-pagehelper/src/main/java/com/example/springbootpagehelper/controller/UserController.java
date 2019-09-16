package com.example.springbootpagehelper.controller;

import com.example.springbootpagehelper.domain.User;
import com.example.springbootpagehelper.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
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
    public List<User> listUser(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageNum) {
        //分页查询，包括分页和总数查询，第三个参数是控制是否计算总数，默认是true,true为查询总数，分页效果只对其后的第一个查询有效。
        PageHelper.startPage(pageNo, pageNum);
        //有分页
        List<User> userList = userService.listUser();
        //没分页
        List<User> users = userService.listUser();

        return userList;
    }

    @RequestMapping(value = "pageInfoUser")
    public PageInfo<User> pageInfoUser(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageNum) {
        //分页查询，包括分页和总数查询，第三个参数是控制是否计算总数，默认是true,true为查询总数，分页效果只对其后的第一个查询有效。
        PageHelper.startPage(pageNo, pageNum);

        PageInfo<User> userPageInfo = userService.pageInfoUser();

        return userPageInfo;
    }

    @RequestMapping(value = "pageUser")
    public Page<User> pageUser(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "3") int pageNum) {
        //分页查询，包括分页和总数查询，第三个参数是控制是否计算总数，默认是true,true为查询总数，分页效果只对其后的第一个查询有效。
        PageHelper.startPage(pageNo, pageNum);

        Page<User> userPage = userService.pageUser();

        return userPage;
    }
}
