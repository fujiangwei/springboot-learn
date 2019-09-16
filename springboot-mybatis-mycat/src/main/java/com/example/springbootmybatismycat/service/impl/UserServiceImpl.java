package com.example.springbootmybatismycat.service.impl;

import com.example.springbootmybatismycat.domain.User;
import com.example.springbootmybatismycat.mapper.UserMapper;
import com.example.springbootmybatismycat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/1/31
 * @time: 21:56
 * @modifier:
 * @since:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listUser() {
        return userMapper.selectUserList();
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }
}
