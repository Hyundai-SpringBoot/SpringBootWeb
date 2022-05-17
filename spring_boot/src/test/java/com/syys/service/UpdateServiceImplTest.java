package com.syys.service;
/*************************************************************
파일명: UpdateServiceImlTest
기능: 회원정보수정 테스트
작성자: 이승연
*************************************************************/


import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.syys.entity.Member;
import com.syys.mapper.MemberRepository;
@SpringBootTest
public class UpdateServiceImplTest {
	@Autowired
	private MemberRepository mapper; 
	@Autowired
	private PasswordEncoder passwordencoder; 
	@Test
	public void testUpdateMember() throws SQLException {
		Member member = new Member();
		member.setMid("isaa1100");
		member.setMname("이승연");
		member.setMpassword(passwordencoder.encode("1111"));
		member.setMemail("isaa1107@naver.com");
		member.setSocial(0);
		mapper.updateMember(member);
	}

}

