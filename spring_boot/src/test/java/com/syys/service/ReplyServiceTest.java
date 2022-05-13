package com.syys.service;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan(basePackages = "com.syys.mapper")
public class ReplyServiceTest {
	@Autowired
	private ReplyService  service;
	
	@Test
	public void testExists() {
		System.out.println(service);
		service.list(1).forEach(
				i -> System.out.println(i)
			);	
	}//end test

}
