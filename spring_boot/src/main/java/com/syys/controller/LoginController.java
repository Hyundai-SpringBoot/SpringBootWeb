package com.syys.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.syys.dto.AuthMemberDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/login")
public class LoginController {
	@GetMapping("/all") // 로그인을 하지 않은 사용자도 접근할 수 있다.
	public void ALL() {
		log.info("All.....");

	}

	@GetMapping("/member") // 로그인한 사용자만이 접근할 수 있다.
	public void Member(@AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
		log.info("Member.....");
		log.info(authMemberDTO);
	}

	// @PreAuthorize("hasRole='ADMIN'")
	@GetMapping("/admin") // 관리자 권한이 있는 사용자만이 접근할 수 있다.
	public void Admin() {
		log.info("Admin.....");

	}

	@GetMapping("/login")
	public void login() {
		log.info("usual_login......");
	}

	
}
