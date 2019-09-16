package com.example.springbootpagehelper.service;

import com.example.springbootpagehelper.domain.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/1/31
 * @time: 21:55
 * @modifier:
 * @since:
 */
public interface UserService {

    List<User> listUser();

    PageInfo<User> pageInfoUser();

    Page<User> pageUser();
}
