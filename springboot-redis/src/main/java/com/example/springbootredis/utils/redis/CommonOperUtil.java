package com.example.springbootredis.utils.redis;

import redis.clients.jedis.Jedis;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/22
 * @time: 22:04
 * @modifier:
 * @since:
 */
public class CommonOperUtil {

    public void oper(Jedis jedis) {
        //是否存在key
        Boolean exists = jedis.exists("append");
        Boolean exists2 = jedis.exists("exists2");
        System.out.println("exists :" + exists + "---" + exists2);

        //是否存在keys
        Long exists1 = jedis.exists("append", "exists2");
        System.out.println("exists :" + exists1);

        //删除key
        Long del = jedis.del("incr");
        System.out.println("del :" + del);
        Long del1 = jedis.del("k1", "k2", "k3", "k8", "k9");
        System.out.println("del1 :" + del);

        //类型
        String type = jedis.type("remain_num_key");
        System.out.println("类型:" + type);
    }
}
