/*************************************************************
파일명: BoardServiceTest.java
작성자: 진영서
*************************************************************/
package com.syys.service;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.syys.domain.BoardVO;
import com.syys.domain.Pager;

@SpringBootTest
public class BoardServiceTest {
	@Autowired
    private BoardService boardService;
   
    @Test
    public void ServiceTest1() {       
    BoardVO board=boardService.get(1, true);
    System.out.println(board);
    }
}
