package com.syys.service;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.syys.domain.ReplyVO;

@SpringBootTest
@MapperScan(basePackages = "com.syys.mapper")
public class ReplyServicecTest3 {
	@Autowired
	private ReplyService  service;
	
	@Test
	public void testExists() {
		ReplyVO board = new ReplyVO() ;
		//실행전 존재하는 번호인지 확인
		board.setRno(6);
		board.setReplytext("service 수정된 내용");
		board.setMid("user00");		
		int count = service.update(board);
		System.out.println("Update COUNT" + count);
	}//end test

}
