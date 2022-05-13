package com.syys.service;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.syys.domain.ReplyVO;

@SpringBootTest
@MapperScan(basePackages = "com.syys.mapper")
public class ReplyServiceTest2 {
	@Autowired
	private ReplyService  service;
	
	@Test
	public void testExists() {
		System.out.println(service);
		ReplyVO board = new ReplyVO() ;
		board.setBno(11); //db에서 확인후 진행		
		board.setReplytext("서비스 새로작성하는글");
		board.setMid("newbie");
		service.insert(board);
		System.out.println(board);
	}//end test

}
