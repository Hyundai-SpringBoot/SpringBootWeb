package com.syys.controller;

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

	@GetMapping("")
	public String singUp() {
		return "login/signUp";
	}

	@PostMapping("")
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
