package com.example.springbootjpa.service.impl;

import com.example.springbootjpa.domain.User;
import com.example.springbootjpa.repository.UserRepository;
import com.example.springbootjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/17
 * @time: 14:14
 * @modifier:
 * @since:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> listUser() {
        return userRepository.findAllBy();
    }

    @Override
    public User findByUserId(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User findUser(String name) {
        return userRepository.findUser(name);
    }
}
