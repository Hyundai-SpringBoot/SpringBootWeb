/*************************************************************
파일명: BoardTest7.java
작성자: 진영서
*************************************************************/
package com.syys.mapper;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

 
@SpringBootTest

public class BoardTest7 {
    @Autowired
    private BoardMapper mapper;
   
    @Test
    public void UpdateTest2() {
        //디비에서 행존재 확인
        int total =mapper.updatehit(1);
        System.out.println(total);
        System.out.println("-------------");       
    }//end tets

}
