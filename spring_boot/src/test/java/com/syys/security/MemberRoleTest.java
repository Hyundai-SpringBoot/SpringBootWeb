/*************************************************************
파일명: MemberRoleTest.java
작성자: 이승연
*************************************************************/
package com.syys.security;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.syys.entity.MemberRole;
import com.syys.entity.RoleSet;
import com.syys.mapper.MemberRepository;

@SpringBootTest
public class MemberRoleTest {
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void insertDummies2() throws SQLException{
		for(int i=1; i<=101; i++) {
			RoleSet	roleset = new RoleSet();
			String role=null;
			roleset.setMid("user"+i);
			role = MemberRole.USER.toString();
			if(i>80) {
				role =MemberRole.MANAGER.toString();
			}
			if (i>90) {
				role = MemberRole.ADMIN.toString();
			}
			roleset.setRole_set(role);
			memberRepository.insertRoleSet(roleset);
		}
	}

}
