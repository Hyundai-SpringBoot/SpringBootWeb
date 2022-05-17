/*************************************************************
파일명: BoardTest5.java
작성자: 진영서
*************************************************************/
package com.syys.mapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.syys.domain.BoardVO;
@SpringBootTest
public class BoardTest5 {
	@Autowired
    private BoardMapper mapper;
   
    @Test
    public void UpdateTest() {
        BoardVO board = new BoardVO();
        board.setBno(11); 
        board.setContent("mapper update content");
        int totol =mapper.update(board);
        System.out.println(totol);
        System.out.println("-------------");       
    }

}
