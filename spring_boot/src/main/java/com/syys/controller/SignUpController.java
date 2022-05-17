package com.syys.controller;
/*************************************************************
파일명: SignUpController.java
기능: 회원가입 컨트롤러 
작성자: 이승연
*************************************************************/
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.syys.entity.Member2;
import com.syys.service.signUpService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/signUp")
public class SignUpController {
	@Autowired
	signUpService service;

	@GetMapping("") //회원 가입 폼으로 이동
	public String singUp() {
		return "login/signUp";
	}

	@PostMapping("") //회원 가입 데이터 입력 후, DB연동 , Json으로 반환해서 Ajax로 데이터 처리
	@ResponseBody
	public Map<String, Object> data(@RequestParam("mid") String mid, @RequestParam("mpassword") String password,
			@RequestParam("mname") String name, @RequestParam("memail") String email) {
		Member2 vo = new Member2();
		vo.setMid(mid);
		vo.setMemail(email);
		vo.setMpassword(password);
		vo.setMname(name);
		
		Map<String, Object> map = new HashMap<>();
		
		try {
			service.insertMember(vo);
		} catch (SQLException e) {

			e.printStackTrace();
			map.put("result", "SignUp Fail");
			return map;
		}

		map.put("result", "success");
		return map;

	}

}
