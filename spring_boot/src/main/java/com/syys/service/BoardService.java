package com.syys.service;

import java.util.List;

import com.syys.domain.BoardVO;
import com.syys.domain.Pager;

public interface BoardService {
		// 게시판 글 총 개수 불러오기
		public int getTotal();

		// 게시판 글 작성
		public void register(BoardVO board);
		
		// 게시판 글 조회
		public BoardVO get(int bno);
		
		// 게시판 글 수정
		public void modify(BoardVO board);
		
		// 게시판 글 삭제
		public void remove(int bno);
		
		// 페이징 적용된 게시판 글 목록 조회
		public List<BoardVO> getList(Pager pager);
		
	
}
