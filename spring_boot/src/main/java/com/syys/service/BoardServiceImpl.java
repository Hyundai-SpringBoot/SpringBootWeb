package com.syys.service;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syys.domain.BoardVO;
import com.syys.domain.Pager;
import com.syys.mapper.BoardMapper;
import com.syys.mapper.ReplyBoard;

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
	
	// QnA 게시판 글 작성
	@Override
	public void register(BoardVO board) {
		log.info("[register]"+board);
		mapper.insert(board);
		log.info("insert 완료");
	}

	// QnA 게시판 글 조회
	@Override
	public BoardVO get(int bno) {
		log.info("[get]"+bno);
		return mapper.read(bno);
	}

	// QnA 게시판 글 수정
	@Override
	public void modify(BoardVO board) {
		log.info("[modify]"+board);
		mapper.update(board);
	}

	// QnA 게시판 글 삭제
	@Override
	public void remove(int bno) {
		log.info("[remove]"+bno);
		mapper.delete(bno);
	}
	
	// 페이징 적용하지 않은 QnA 게시판 글 목록 조회
//	@Override
//	public List<QnAVO> getList() {
//		log.info("[get list]");
//		return mapper.getList();
//	}
	
	// 페이징 적용된 QnA 게시판 글 목록 조회
	@Override
	public List<BoardVO> getList(Pager pager){
		log.info("[get List with pager] "+pager);
		return mapper.getListWithPaging(pager);
	}
	
	// QnA 게시판 글 총 개수 불러오기
	@Override
	public int getTotal() {
		log.info("[get total count]");
		return mapper.getTotalCount();
	}

}
