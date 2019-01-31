package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.domain.User;

import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/1/31
 * @time: 21:43
 * @modifier:
 * @since:
 */
public interface UserMapper {

    List<User> selectUserList();
}
