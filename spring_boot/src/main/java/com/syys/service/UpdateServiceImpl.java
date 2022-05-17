package com.syys.service;
/*************************************************************
파일명: UpdateServiceImpl.java
기능: 수정 서비스 기능 구현
작성자: 이승연
*************************************************************/
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.syys.entity.Member;
import com.syys.mapper.MemberRepository;

@Service
public class UpdateServiceImpl implements UpdateService {
	@Autowired
	private MemberRepository mapper;
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	@Override
	public void updateMember(Member member) throws SQLException{ //회원정보 수정
		member.setMpassword(passwordencoder.encode(member.getMpassword()));
		mapper.updateMember(member);
		
		
		
	}

	
}
