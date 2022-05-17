package com.syys.service;

import java.sql.SQLException;

import com.syys.entity.Member2;

public interface signUpService {
	public boolean insertMember(Member2 member2) throws SQLException;
}
