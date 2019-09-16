package com.example.springbootmybatismycat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.springbootmybatismycat.mapper"})
public class SpringbootMybatisMycatApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisMycatApplication.class, args);
    }

}
