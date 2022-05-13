package com.syys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Controller
@RequestMapping("login")
public class LoginController {
	@GetMapping(value = "login")
	public String insert() {
		log.info("login");
		return "login/login";
	}//end insert

}
