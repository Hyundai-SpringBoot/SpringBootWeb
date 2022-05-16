/*************************************************************
파일명: BoardServiceImpl.java
기능: 게시글 추가, 수정, 삭제, 게시물 목록 조회(페이징O)를 수행하는 Service 구현 클래스
작성자: 진영서

[코멘트: 글 조회시 hit(true/false)를 통해 조회수 업데이트]
*************************************************************/
package com.syys.service;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syys.domain.BoardVO;
import com.syys.domain.Pager;
import com.syys.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
@AllArgsConstructor
@MapperScan(basePackages = "com.syys.mapper.BoardMapper")
public class BoardServiceImpl implements BoardService {

	// Mapper 객체 주입
	@Autowired
	private BoardMapper mapper;
	
	// 게시판 글 작성
	@Override
	public void register(BoardVO board) {
		log.info("[register]"+board);
		mapper.insert(board);
		log.info("insert 완료");
	}

	// 게시판 글 조회(조회수 반영)
	@Override
	public BoardVO get(int bno, boolean hit) {
		log.info("[get]"+bno);
		if(hit) {
			mapper.updatehit(bno);
		}
		return mapper.read(bno);
	}

	// 게시판 글 수정
	@Override
	public void modify(BoardVO board) {
		log.info("[modify]"+board);
		mapper.update(board);
	}

	// 게시판 글 삭제
	@Override
	public void remove(int bno) {
		log.info("[remove]"+bno);
		mapper.delete(bno);
	}
	
	// 페이징 적용된 게시판 글 목록 조회
	@Override
	public List<BoardVO> getList(Pager pager){
		log.info("[get List with pager] "+pager);
		return mapper.getListWithPaging(pager);
	}
	
	// 게시판 글 총 개수 불러오기
	@Override
	public int getTotal() {
		log.info("[get total count]");
		return mapper.getTotalCount();
	}

}
