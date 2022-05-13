package com.syys.service;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.syys.domain.ReplyVO;

@SpringBootTest
@MapperScan(basePackages = "com.syys.mapper")
public class ReplySereviceTest4 {
	@Autowired
	private ReplyService  service;
	
	@Test
	public void testExists() {
		System.out.println(service);
		//디비에 있는지 확인
		int count = service.delete(4);
		System.out.println("Delete COUNT" + count);
	}//end test

}
