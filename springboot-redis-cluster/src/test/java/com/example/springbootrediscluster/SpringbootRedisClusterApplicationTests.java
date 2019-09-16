package com.example.springbootrediscluster;

import com.example.springbootrediscluster.config.client.RedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisClusterApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private RedisClient redisClient;

    @Test
    public void redis() {
        redisClient.set("slot_curr_num_key", 6);

        System.out.println(redisClient.get("slot_curr_num_key"));
    }

}
