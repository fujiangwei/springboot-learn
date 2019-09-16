package com.example.springbootredis.utils.redis;

import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * descripiton: List 列表操作
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/2/22
 * @time: 22:04
 * @modifier:
 * @since:
 */
public class HashOperUtil {

    public static void oper(Jedis jedis) {

        Long id = jedis.hset("hash", "id", "1");
        Long name = jedis.hset("hash", "name", "fjw");
        System.out.println("id:" + id + "---name:" + name);
        String id2 = jedis.hget("hash", "id");
        System.out.println("id2:" + id2 + "---type:" + jedis.type("hash"));
        Map<String, String> hgetAll = jedis.hgetAll("hash");
        System.out.println("hgetAll:" + hgetAll);

    }
}
