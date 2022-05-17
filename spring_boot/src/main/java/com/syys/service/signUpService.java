/*************************************************************
파일명: signUpService.java
기능: 회원가입 서비스
작성자: 이승연
*************************************************************/
package com.syys.service;

import java.sql.SQLException;

import com.syys.entity.Member2;

public interface signUpService {
	public boolean insertMember(Member2 member2) throws SQLException;
}
