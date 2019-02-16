package com.example.springbootfreemarker;

import com.example.springbootfreemarker.utils.FreeMarkerTemplateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootFreemarkerApplicationTests {

	@Test
	public void contextLoads() {
	}

	//填充模板文件中的参数值
	Map<String, Object> root = null;
	FreeMarkerTemplateUtil freeMarkerTemplateUtil = null;

	@Before
	public void setUp(){
		freeMarkerTemplateUtil = new FreeMarkerTemplateUtil();
		root = new HashMap<String, Object>();
	}

	@Test
	public void testCreateHtml() throws Exception{

		root.put("username", "admin");

		String emailHtml = freeMarkerTemplateUtil.getEmailHtml(root, "reg.ftl");
//		System.out.println(">>>>" + emailHtml);

		root.put("username", "root");
		freeMarkerTemplateUtil.print("reg.ftl", root);

	}
}

