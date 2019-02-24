package com.example.springbootdruidmultsource;

import com.example.springbootdruidmultsource.domain.master.SysUser;
import com.example.springbootdruidmultsource.domain.slave.User;
import com.example.springbootdruidmultsource.service.SysUserService;
import com.example.springbootdruidmultsource.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDruidMultsourceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private UserService userService;

	@Test
	public void test() {
		List<SysUser> sysUsers = sysUserService.listUser();
		System.out.println("sysUsers:" + sysUsers.size());

//		List<User> users = userService.listUser();
//		System.out.println("users:" + users.size());
	}

	@Test
	public void transaction() {
//		SysUser sysUser = new SysUser();
//		sysUser.setUserId(1);
//		sysUser.setUserName("name-" + LocalDateTime.now().getHour() + "-" + LocalDateTime.now().getSecond());
//		sysUserService.update(sysUser);

		User user = new User();
		user.setUserId(6);
		user.setUserName("name-" + LocalDateTime.now().getHour() + "-" + LocalDateTime.now().getSecond());
		userService.update(user);
	}

}
