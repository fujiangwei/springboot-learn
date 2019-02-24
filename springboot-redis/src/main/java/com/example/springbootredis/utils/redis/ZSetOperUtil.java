package com.example.springbootredis.utils.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * descripiton: ZSet 有序集合操作
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/22
 * @time: 22:04
 * @modifier:
 * @since:
 */
public class ZSetOperUtil {

    public static void oper(Jedis jedis) {

        jedis.zadd("zset", 3,"3");
        jedis.zadd("zset", 4,"5");
        jedis.zadd("zset", 5,"5");
        jedis.zadd("zset", 6,"6");
        Set<String> zset = jedis.zrange("zset", 0, -1);
        System.out.println("zset:" + zset);
        System.out.println("zset type:" + jedis.type("zset"));

    }
}
