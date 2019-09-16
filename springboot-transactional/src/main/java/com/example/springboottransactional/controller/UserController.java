package com.example.springboottransactional.controller;

import com.example.springboottransactional.domain.User;
import com.example.springboottransactional.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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

    @GetMapping(value = "listUser")
    public List<User> listUser() {
        return userService.listUser();
    }

    @GetMapping(value = "selectUserById")
    public User selectUserById(Integer id) {
        User user = userService.selectUserById(id);
        return user;
    }

    @DeleteMapping(value = "delete")
    public Object delete(Integer id) {
        userService.delete(id);
        return "yes";
    }

    @GetMapping(value = "update")
    public String update(User user) {
        user.setUserName("whh" + "-" + LocalDateTime.now().getHour() + "-" + LocalDateTime.now().getSecond());
        userService.update(user);
        return "yes";
    }
}
