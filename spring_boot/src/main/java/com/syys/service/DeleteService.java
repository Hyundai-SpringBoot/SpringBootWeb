package com.syys.service;

import java.sql.SQLException;

import com.syys.entity.Member;

public interface DeleteService {
	public void delete(String mid) throws SQLException;
}
