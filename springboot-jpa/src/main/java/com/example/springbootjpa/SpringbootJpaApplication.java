package com.example.springbootjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//spring在加载的时候找到我们自定义的BaseRepository的工厂
//@EnableJpaRepositories(basePackages = {"com.example.springbootjpa"},
//		repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class) //指定自己的工厂类
public class SpringbootJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

}
