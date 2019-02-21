package com.example.springboottransactional;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"com.example.springboottransactional.mapper"})
public class SpringbootTransactionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTransactionalApplication.class, args);
	}

}
