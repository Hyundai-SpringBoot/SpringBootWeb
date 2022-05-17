package com.syys.controller;
/*************************************************************
파일명: ModifyControllerTest.java
기능: 회원가입 컨트롤러 테스트
작성자: 이승연
*************************************************************/
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
class ModifyControllerTest {
	@Autowired
	   MockMvc mockMvc;
	@Test
	public void testUpdate_view() throws Exception {
		log.info(
	            mockMvc.perform(MockMvcRequestBuilders.get("/update"))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andReturn().getModelAndView().getViewName()
	            
	   );
	}

	@Test
	void testUpdate() throws Exception {
		log.info(
	            mockMvc.perform(MockMvcRequestBuilders.get("/update/modify"))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andReturn().getModelAndView().getViewName()
	            
	   );
	}

	@Test
	void testDelete() throws Exception {
		log.info(
	            mockMvc.perform(MockMvcRequestBuilders.get("/update/delete"))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andReturn().getModelAndView().getViewName()
	            
	   );
	}

}
