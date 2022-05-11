package com.syys.service;

import java.util.List;

import com.syys.domain.ReplyVO;

public interface ReplyService {

public List<ReplyVO> list( int bno) ;
    public void insert(ReplyVO vo) ;	
	public int update(ReplyVO vo) ;	
	public int delete(int rno) ;	

}
