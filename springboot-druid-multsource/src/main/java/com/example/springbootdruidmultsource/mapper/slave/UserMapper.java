package com.example.springbootdruidmultsource.mapper.slave;

import com.example.springbootdruidmultsource.domain.slave.User;
import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface UserMapper {

    List<User> selectUserList();

    void update(User user);
}
