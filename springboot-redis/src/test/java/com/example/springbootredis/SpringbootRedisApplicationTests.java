package com.example.springbootredis;

import com.example.springbootredis.config.RedisConfig;
import com.example.springbootredis.config.client.RedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
		System.out.println(redisConfig);

		redisClient.set("hello", "hello, redis");

		System.out.println(redisClient.get("hello"));

	}

}
