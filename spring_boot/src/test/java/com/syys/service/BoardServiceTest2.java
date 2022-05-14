package com.syys.service;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.syys.domain.BoardVO;
import com.syys.domain.Pager;

@SpringBootTest
public class BoardServiceTest2 {
	@Autowired
    private BoardService boardService;
   
    @Test
    public void ServiceTest1() {
    int totalRows = boardService.getTotal();
    Pager pager = new Pager(5,5,totalRows,1);
    List<BoardVO> list=boardService.getList(pager);
    for (BoardVO i : list) {
        System.out.println(i);      
    }//end for
    System.out.println("----------");
    }

}
