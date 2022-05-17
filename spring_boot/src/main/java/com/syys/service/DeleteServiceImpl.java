/*************************************************************
파일명: DeleteServiceImpl.java
기능: 회원정보 삭제 서비스 구현
작성자: 이승연
*************************************************************/
package com.syys.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syys.mapper.MemberRepository;

@Service
public class DeleteServiceImpl implements DeleteService{
	@Autowired
	private MemberRepository mapper; 
	
	@Override
	public void delete(String mid) throws SQLException {
		mapper.delete(mid);
	}
}
