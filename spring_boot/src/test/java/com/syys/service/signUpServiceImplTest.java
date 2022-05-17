package com.syys.service;
/*************************************************************
파일명: signUpServiceImplTest
기능: 회원가입 JUnitTest
작성자: 이승연
*************************************************************/
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.syys.entity.Member2;
import com.syys.mapper.MemberRepository;
@SpringBootTest
public class signUpServiceImplTest {
	@Autowired
	private MemberRepository mapper; 
	@Autowired
	private PasswordEncoder passwordencoder; 
	@Test
	public void testInsertMember() throws SQLException {
		Member2 member2 = new Member2();
		member2.setMid("isaa1100");
		member2.setMpassword(passwordencoder.encode("1111"));
		member2.setMname("이승연");
		member2.setRole_set("USER");
		mapper.insertMember(member2);
	}

}
