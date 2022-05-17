package com.syys.mapper;
/*************************************************************
파일명: MemberRepository
기능: 회원정보수정 테스트
작성자: 이승연
*************************************************************/
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.syys.entity.Member;
import com.syys.entity.Member2;
import com.syys.entity.RoleSet;
@SpringBootTest
public class MemberRepositoryTest {
	@Autowired
	private MemberRepository mapper;
	@Autowired
	private PasswordEncoder passwordencoder; 
	@Test
	public void testInsertMember() throws SQLException {
		Member2 member2=new Member2();
		member2.setMid("isaa11");
		member2.setMpassword(passwordencoder.encode("1111"));
		member2.setMname("이승연");
		member2.setRole_set("USER");
		mapper.insertMember(member2);
	}

	@Test
	public void testInsertRoleSet() throws SQLException{
		RoleSet roleset = new RoleSet();
		roleset.setMid("isaa11");
		roleset.setRole_set("USER");
		mapper.insertRoleSet(roleset);
	}

	@Test
	public void testFindById() throws SQLException{
		String mid="isaa12";
		mapper.findById(mid, 0);
	}
	@Test
	public void testUpdateMember() throws SQLException{
		Member member = new Member();
		member.setMid("isaa13");
		member.setMname("이승연");
		member.setMpassword(passwordencoder.encode("1111"));
		member.setMemail("isaa1107@naver.com");
		member.setSocial(0);
		mapper.updateMember(member);
	}
	@Test
	public void testDelete() throws SQLException{
		String mid="isaa1122";
		mapper.delete(mid);
	}

}
