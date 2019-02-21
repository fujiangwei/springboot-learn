package com.example.springbootcache.service.impl;

import com.example.springbootcache.domain.User;
import com.example.springbootcache.mapper.UserMapper;
import com.example.springbootcache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/1/31
 * @time: 21:56
 * @modifier:
 * @since:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //使用ehcache配置的缓存名users_test
    private final String USER_CACHE_NAME = "users_test";

    @Override
    public List<User> listUser() {
        return userMapper.selectUserList();
    }

    @Override
//    @Cacheable(value = USER_CACHE_NAME, key = "#id")
    @Cacheable(value = USER_CACHE_NAME, key = "'user' + #id")
    public User selectUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
//    @CacheEvict(value = USER_CACHE_NAME, key = "#id")
    @CacheEvict(value = USER_CACHE_NAME, key = "'user_' + #id")
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    @Override
//    @CacheEvict(value = USER_CACHE_NAME, key = "#user.userId")
    @CacheEvict(value = USER_CACHE_NAME, key = "'user' + #user.userId")
//    @CachePut(value = USER_CACHE_NAME, key = "'user' + #user.userId") //测试发现只将结果清除，key为清除，导致查询继续使用缓存但结果为空
    public void update(User user) {
        userMapper.update(user);
    }
}
