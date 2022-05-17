package com.syys.security;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.syys.entity.Member;
import com.syys.entity.Member2;
import com.syys.mapper.MemberRepository;

@SpringBootTest
public class MemberTest {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void insertDummies() throws SQLException{
		for (int i=1; i<=101; i++) {
			Member2 member = new Member2();
			member.setMid("user"+i);
			member.setMemail("isaa1107@naver.com");
			member.setMname("황재성"+i);
			member.setSocial(0);
			member.setMpassword(passwordEncoder.encode("1111"));
			memberRepository.insertMember(member);
		}
		System.out.println("입력 성공");
	}
}
