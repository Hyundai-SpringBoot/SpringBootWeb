/*************************************************************
파일명: signUpServiceImpl.java
기능: 회원가입서비스 기능 구현
작성자: 이승연
*************************************************************/
package com.syys.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.syys.entity.Member2;
import com.syys.entity.RoleSet;
import com.syys.mapper.MemberRepository;
@Service
public class signUpServiceImpl implements signUpService{
	@Autowired
	private MemberRepository mapper;
	
	@Autowired
	private PasswordEncoder passwordencoder; 
	
	public boolean insertMember(Member2 member2) throws SQLException {
		if(mapper.findById(member2.getMid(), 0)!=null) {
			return false;
		}
		member2.setMpassword(passwordencoder.encode(member2.getMpassword())); //암호 인코딩
		mapper.insertMember(member2); //mapper 연동
		RoleSet roleset = new RoleSet(member2.getMid(),"USER"); //RoleSet 세팅
		mapper.insertRoleSet(roleset);//권한 삽입
		return true;
	}
	
}
