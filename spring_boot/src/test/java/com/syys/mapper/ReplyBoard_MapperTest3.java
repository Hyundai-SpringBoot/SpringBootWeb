package com.syys.mapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.syys.domain.ReplyVO;

@SpringBootTest
public class ReplyBoard_MapperTest3 {
	@Autowired
	private ReplyBoard mapper;
	
	@Test
	public void testUpdate() {
		ReplyVO board = new ReplyVO() ;
		//실행전 존재하는 번호인지 확인
		board.setRno(5);
		board.setReplytext("5수정된 내용");
		board.setMid("user00");		
		int count = mapper.update(board);
		System.out.println("Update COUNT" + count);				
	}

}
