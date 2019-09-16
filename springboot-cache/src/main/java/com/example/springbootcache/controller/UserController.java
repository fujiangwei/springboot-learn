package com.example.springbootcache.controller;

import com.example.springbootcache.domain.User;
import com.example.springbootcache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    /**
     * 默认为ConcurrenMapCacheManager
     */
    @Autowired
    private CacheManager cacheManager;

    @GetMapping(value = "listUser")
    public List<User> listUser() {
        return userService.listUser();
    }

    @GetMapping(value = "selectUserById")
    public User selectUserById(String id) {
        Integer userId = Integer.parseInt(id);
        User user = userService.selectUserById(userId);
        User user1 = userService.selectUserById(userId);
        return user1;
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
