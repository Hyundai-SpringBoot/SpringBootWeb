package com.syys.mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.syys.domain.BoardVO;

@SpringBootTest
public class BoardTest6 {
	@Autowired
    private BoardMapper mapper;
   
    @Test
    public void DeleteTest() {
        //디비에서 행존재 확인
        int total =mapper.delete(10);
        System.out.println(total +"행삭제");
        System.out.println("-------------");       
    }

}
