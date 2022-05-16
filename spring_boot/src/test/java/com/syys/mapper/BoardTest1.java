package com.syys.mapper;
/*************************************************************
파일명: BoardTest1.java
작성자: 진영서
*************************************************************/
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardTest1 {
    @Autowired
    private BoardMapper mapper;
   
    @Test
    public void ListTest() {
        int total = mapper.getTotalCount();
        System.out.println(total);       
        System.out.println("-------------");
       
    }

}
