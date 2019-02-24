package com.example.springbootqiniu;

import com.example.springbootqiniu.storage.AliOssStorage;
import com.example.springbootqiniu.storage.QiNiuStorage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootStorageApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private QiNiuStorage qiNiuStorage;

	@Autowired
	private AliOssStorage aliOssStorage;

	@Test
	public void test(){
//		qiNiuStorage.upload();
//		qiNiuStorage.deleteFile();
		aliOssStorage.upload();
		aliOssStorage.delete();
	}

}
