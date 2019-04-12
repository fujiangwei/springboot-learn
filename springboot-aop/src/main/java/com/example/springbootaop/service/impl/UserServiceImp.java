package com.example.springbootaop.service.impl;

import com.example.springbootaop.service.UserService;
import org.springframework.stereotype.Repository;

@Repository
public class UserServiceImp implements UserService {
    @Override
    public int addUser() {
        System.out.println("add user ......");
        return 6666;
    }

    @Override
    public void updateUser() {
        System.out.println("update user ......");
    }

    @Override
    public void deleteUser() {
        System.out.println("delete user ......");
    }

    @Override
    public void findUser() {
        System.out.println("find user ......");
    }
}