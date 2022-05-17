package com.syys.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.syys.service.DeleteService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller

public class DeleteController {
	@Autowired
	private DeleteService service;
	
	
}
