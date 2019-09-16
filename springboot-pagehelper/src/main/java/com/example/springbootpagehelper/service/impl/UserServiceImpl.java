package com.example.springbootpagehelper.service.impl;

import com.example.springbootpagehelper.domain.User;
import com.example.springbootpagehelper.mapper.UserMapper;
import com.example.springbootpagehelper.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<User> pageInfoUser() {
        return new PageInfo<>(userMapper.selectUserPage());
    }

    @Override
    public Page<User> pageUser() {
        return userMapper.selectUserPage();
    }
}
