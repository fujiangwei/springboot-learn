package com.example.springbootjpa;

import com.example.springbootjpa.domain.User;
import com.example.springbootjpa.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJpaApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private UserService userService;

	@Test
	public void test() {
//		System.out.println(userService.findByUserId(1));
//		System.out.println(userService.findUser("hzq"));
		List<User> users = userService.listUser();
		System.out.println(users.size());
	}

}
