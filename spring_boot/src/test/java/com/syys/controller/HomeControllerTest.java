/*************************************************************
파일명: HomeControllerTest.java
기능: 게시판 관련 Controller test 구현
작성자: 진영서

[코멘트: X]
*************************************************************/
package com.syys.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
public class HomeControllerTest {
   
   @Autowired
   MockMvc mockMvc;
   
   @Test
   public void test01_home() throws Exception{ // home 페이지로 이동 테스트
      log.info(
            mockMvc.perform(MockMvcRequestBuilders.get("/board"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn().getModelAndView().getViewName()
            
   );
   }
}