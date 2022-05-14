package com.syys.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.syys.domain.Pager;
@SpringBootTest
public class BoardTest2 {
	@Autowired
    private BoardMapper mapper;
   
    @Test
    public void ListTest() {
        int total = mapper.getTotalCount();
        System.out.println(total);
        Pager pager = new Pager(5, 5, total, 1);
        mapper.getListWithPaging(pager).forEach(
                i -> System.out.println(i)
                );  
        System.out.println("-------------");
       
    }

}
