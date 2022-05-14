package com.syys.mapper;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.syys.domain.ReplyVO;

@SpringBootTest
public class ReplyBoard_MapperTest2 {
	@Autowired
	private ReplyBoard mapper;
	
	@Test
	public void testInsert() {
		ReplyVO board = new ReplyVO() ;
		board.setBno(10); //db에서 확인후 진행		
		board.setReplytext("새로작성하는글");
		board.setMid("newbie");
		mapper.create(board);
		System.out.println(board);
	}
}
