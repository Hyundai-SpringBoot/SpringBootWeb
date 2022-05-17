/*************************************************************
파일명: BoardTest4.java
작성자: 진영서
*************************************************************/
package com.syys.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.syys.domain.BoardVO;
@SpringBootTest
public class BoardTest4 {
	@Autowired
    private BoardMapper mapper;
   
    @Test
    public void ListTest() {
        BoardVO board = new BoardVO();
        board.setContent("mapper test content");
        board.setMid("ys");
        mapper.insert(board);
        int total = mapper.getTotalCount();
        System.out.println(total);
        System.out.println("-------------");
       
    }

}
