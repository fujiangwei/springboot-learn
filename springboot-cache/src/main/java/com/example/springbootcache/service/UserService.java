package com.example.springbootcache.service;

import com.example.springbootcache.domain.User;

import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/1/31
 * @time: 21:55
 * @modifier:
 * @since:
 */
public interface UserService {

    List<User> listUser();

    User selectUserById(final Integer id);

    void delete(final Integer id);

    void update(final User user);
}
