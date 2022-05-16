/*************************************************************
파일명: BoardService.java
기능: 게시글 추가, 수정, 삭제, 게시물 목록 조회(페이징O)를 수행하는 Service 인터페이스
작성자: 진영서

[코멘트: X]
*************************************************************/
package com.syys.service;

import java.util.List;

import com.syys.domain.BoardVO;
import com.syys.domain.Pager;

public interface BoardService {
		// 게시판 글 총 개수 불러오기
		public int getTotal();

		// 게시판 글 작성
		public void register(BoardVO board);
		
		// 게시판 글 조회(조회수 반영)
		public BoardVO get(int bno, boolean hit);
		
		// 게시판 글 수정
		public void modify(BoardVO board);
		
		// 게시판 글 삭제
		public void remove(int bno);
		
		// 페이징 적용된 게시판 글 목록 조회
		public List<BoardVO> getList(Pager pager);
		
	
}
