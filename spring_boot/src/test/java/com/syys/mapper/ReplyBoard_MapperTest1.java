package com.syys.mapper;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReplyBoard_MapperTest1 {
	
	@Autowired
	private ReplyBoard mapper;
	
	@Test
	public void testGetList() {
		mapper.list(1).forEach(
				i -> System.out.println(i)
				);		
	}//end void
	
}//end class
