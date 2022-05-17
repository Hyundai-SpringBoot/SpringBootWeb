/*************************************************************
파일명: BoardTest3.java
작성자: 진영서
*************************************************************/
package com.syys.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.syys.domain.BoardVO;

@SpringBootTest
public class BoardTest3 {
	@Autowired
    private BoardMapper mapper;
   
    @Test
    public void ListTest() {        
        BoardVO board= mapper.read(11);
        System.out.println(board);
        System.out.println("-------------");
       
    }

}
