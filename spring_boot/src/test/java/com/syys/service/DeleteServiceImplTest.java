package com.syys.service;
/*************************************************************
파일명: DeleteServiceImplTest
기능: 회원정보 삭제 JUnitTest
작성자: 이승연
*************************************************************/
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.syys.mapper.MemberRepository;

@SpringBootTest
public class DeleteServiceImplTest {
	@Autowired
	private MemberRepository mapper;
	
	@Test
	public void testDelete() throws SQLException {
		 mapper.delete("user9");
	}

}
