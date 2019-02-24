package com.example.springbootredis;

import com.example.springbootredis.config.RedisConfig;
import com.example.springbootredis.config.client.RedisClient;
import com.example.springbootredis.utils.redis.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private RedisClient redisClient;

    @Test
    public void redis() {
//		System.out.println(redisConfig);

        Jedis jedis = redisClient.getJedis();
        /**********String 字符串操作***********/
//        StringOperUtil.oper(jedis);

        /**********List 列表操作***********/
        ListOperUtil.oper(jedis);

        /**********Hash 哈希表操作***********/
        HashOperUtil.oper(jedis);

        /**********Set  集合操作***********/
        SetOperUtil.oper(jedis);

        /**********ZSet  有序集合操作***********/
        ZSetOperUtil.oper(jedis);
    }

}
