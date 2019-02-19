package com.example.springbootjpa.service;

import com.example.springbootjpa.domain.User;

import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/17
 * @time: 14:13
 * @modifier:
 * @since:
 */
public interface UserService {

    List<User> listUser();

    User findByUserId(Integer userId);

    User findUser(String name);

    List<User> findUsersBySql(final String sql, final Class clazz);

    Object findUserBySql(final String sql, final Class clazz);

}
