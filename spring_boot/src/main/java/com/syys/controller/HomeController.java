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
    private BoardService boardService;
	
    @RequestMapping("/board")
    public String home(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        log.info("home 실행");
        log.info("pageNo="+pageNo);
        //게시판테이블 전체 행수 가져오기
        int totalRows = boardService.getTotal();
        //페이징 만들기, 5개
        Pager pager = new Pager(5, 5, totalRows, pageNo);
        model.addAttribute("pager",pager);
        log.info("model"+model);
        model.addAttribute("pageNo",pageNo);
        return "/board/list";
    }
}
