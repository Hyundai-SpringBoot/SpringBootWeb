package com.syys.controller;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.syys.entity.Member;
import com.syys.service.DeleteService;
import com.syys.service.UpdateService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/update")
public class ModifyController {
	@Autowired
	private UpdateService service;
	
	@Autowired
	private DeleteService service2;
	
	
	
	@GetMapping("")
	public String update_view(){
		log.info("update_view");
		return "login/Modify";
	}
	
	@PostMapping("/modify")
	public String update(Member member) throws Exception{
		log.info("이승연"+member);
		
		service.updateMember(member);
		return "redirect:/logout";
	}
	
	@PostMapping("/delete")
	public String delete(String mid) throws SQLException {
		log.info("진영서"+mid);
		service2.delete(mid);
		
		return "redirect:/logout";
	}
	
}
