package com.example.springbootredis.utils.redis;

import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

/**
 * descripiton: Set 集合操作
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/22
 * @time: 22:04
 * @modifier:
 * @since:
 */
public class SetOperUtil {

    public static void oper(Jedis jedis) {

        Long id = jedis.sadd("set", "1");
        Long id2 = jedis.sadd("set", "1", "12");
        Set<String> smembers = jedis.smembers("set");
        System.out.println("smembers:" + smembers);
        System.out.println("set type:" + jedis.type("hash"));

    }
}
