/*************************************************************
파일명: BoardMapper.java
기능: 게시글 조회,작성, 수정, 삭제하는 Mapper 인터페이스
작성자: 진영서

[코멘트: X]
*************************************************************/
package com.syys.mapper;

import java.util.List;

import com.syys.domain.BoardVO;
import com.syys.domain.Pager;

public interface BoardMapper {
		// 게시글 데이터 수 불러오기 (SELECT문 이용)
		public int getTotalCount();
		
		// 글 리스트 조회 - 페이징 적용O (SELECT문 이용)
		public List<BoardVO> getListWithPaging(Pager pager);
		
		// 글 작성 (INSERT문 이용)
		public void insert(BoardVO board);
		
		// 글 조회를 위해 해당 글번호의 Board 객체 반환 (SELECT문 이용)
		public BoardVO read(long bno);
		
		// 글 삭제 (DELETE문 이용)
		public int delete(long bno);
		
		// 글 수정 (UPDATE문 이용)
		public int update(BoardVO board);
		
		//글 조회수 업데이트 (UPDATE문 이용)
		public int updatehit(int bno);
}
