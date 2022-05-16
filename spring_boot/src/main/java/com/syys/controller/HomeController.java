/*************************************************************
파일명: HomeController.java
기능: 글조회 페이지(메인)으로 이동
작성자: 진영서

[코멘트: X]
*************************************************************/
package com.syys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.syys.domain.Pager;
import com.syys.service.BoardService;

import lombok.extern.slf4j.Slf4j;
 
@Controller
@Slf4j
public class HomeController {
	
	@Autowired
	// Service 객체 주입
    private BoardService boardService;
	
	//페이징 적용O
	//게시물 목록 페이지 list.html로 이동
	// Parameter: pageNo(페이지 번호)   
	@RequestMapping("/board")
    public String home(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        log.info("home 실행");
        log.info("pageNo="+pageNo);
        //게시판테이블 전체 행수 가져오기
        int totalRows = boardService.getTotal();
        //페이징 만들기, 5개
        Pager pager = new Pager(5, 5, totalRows, pageNo);
        //게시판 페이징을 위한 "pager", "pageNo" 저장
        model.addAttribute("pager",pager);
        model.addAttribute("pageNo",pageNo);
        return "/board/list"; //list.html로 이동
    }
}
