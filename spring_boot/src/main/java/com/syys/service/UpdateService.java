package com.syys.service;

import java.sql.SQLException;

import com.syys.entity.Member;

public interface UpdateService {
	public void updateMember(Member member) throws SQLException;
}
