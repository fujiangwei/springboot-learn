package com.example.springbootcache.service;

import com.example.springbootcache.domain.User;
import org.springframework.cache.annotation.*;

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
@CacheConfig(cacheNames = "users")
public interface UserService {

    List<User> listUser();

    @Cacheable(value = "user", key = "#id")
    User selectUserById(final Integer id);

    @CacheEvict(value = "user", key = "#id")
    void delete(final Integer id);

    //@CachePut(value = "user", key = "#user.userId") //测试发现只将结果清除，key为清除，导致查询继续使用缓存但结果为空
    @CacheEvict(value = "user", key = "#user.userId")
    void update(final User user);
}
