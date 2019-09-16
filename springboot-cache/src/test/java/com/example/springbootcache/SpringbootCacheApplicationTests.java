package com.example.springbootcache;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootCacheApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Before
    public void before() {
        System.out.println("before test");
    }

    @After
    public void after() {
        System.out.println("after test");
    }

}
