package com.example.springbootscheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
//开启对定时任务的支持，这种方式是在同一个线程中串行执行，适合单个线程任务处理
@EnableScheduling
public class SpringbootScheduledApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootScheduledApplication.class, args);
	}

}
