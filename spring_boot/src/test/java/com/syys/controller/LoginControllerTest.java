package com.syys.controller;
/*************************************************************
파일명: LoginControllerTest.java
기능: 회원가입 컨트롤러
작성자: 이승연
*************************************************************/
import static org.junit.jupiter.api.Assertions.*;

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
public class LoginControllerTest {
	@Autowired
	   MockMvc mockMvc;
	@Test
	public void testALL() throws Exception {
		log.info(
	            mockMvc.perform(MockMvcRequestBuilders.get("/login/all"))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andReturn().getModelAndView().getViewName()
	            
	   );
	}

	@Test
	public void testMember() throws Exception {
		log.info(
	            mockMvc.perform(MockMvcRequestBuilders.get("/login/member"))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andReturn().getModelAndView().getViewName()
	            
	   );
	}

	@Test
	public void testAdmin() throws Exception {
		log.info(
	            mockMvc.perform(MockMvcRequestBuilders.get("/login/admin"))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andReturn().getModelAndView().getViewName()
	            
	   );
	}

	@Test
	public void testLogin() throws Exception {
		log.info(
	            mockMvc.perform(MockMvcRequestBuilders.get("/login/login"))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andReturn().getModelAndView().getViewName()
	            
	   );
	}

}
