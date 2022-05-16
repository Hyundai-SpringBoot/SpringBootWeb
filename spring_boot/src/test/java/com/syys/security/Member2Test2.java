package com.syys.security;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.syys.entity.Member2;
import com.syys.mapper.MemberRepository;

@SpringBootTest
public class Member2Test2 {
	@Autowired
	private MemberRepository MemberRepository;
	
	@Test
	public void testRead() throws SQLException{
		Member2 result=MemberRepository.findById("user1",0);
		System.out.println(result);
	}

}
