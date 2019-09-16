package com.example.springbootredis.utils.redis;

import redis.clients.jedis.Jedis;

/**
 * descripiton: List 列表操作
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/2/22
 * @time: 22:04
 * @modifier:
 * @since:
 */
public class ListOperUtil {

    public static void oper(Jedis jedis) {

        Long lpush = jedis.lpush("list", "a", "b", "c");
        String list = jedis.lpop("list");
        System.out.println("lpush:" + lpush + "---" + list + "---type:" + jedis.type("list"));

    }
}
