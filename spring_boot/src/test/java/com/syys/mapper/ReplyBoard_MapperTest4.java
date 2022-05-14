package com.syys.mapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReplyBoard_MapperTest4 {
	@Autowired
	private ReplyBoard mapper;
	
	@Test
	public void testDelete() {
		//db에 데이터 있는지 확인
		System.out.println("Delete count: " + mapper.delete(3) );
	}//end void	

}
