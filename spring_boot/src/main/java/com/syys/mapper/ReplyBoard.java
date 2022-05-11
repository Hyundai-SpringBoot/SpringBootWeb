package com.syys.mapper;

import java.util.List;

import com.syys.domain.ReplyVO;

public interface ReplyBoard {
	public List<ReplyVO> list( int bno);	
    public void create(ReplyVO vo);	
	public int update(ReplyVO vo);	
	public int delete (int rno);
}
