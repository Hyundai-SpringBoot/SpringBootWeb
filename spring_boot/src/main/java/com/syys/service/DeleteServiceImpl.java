package com.syys.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syys.mapper.MemberRepository;

@Service
public class DeleteServiceImpl implements DeleteService{
	@Autowired
	private MemberRepository mapper; 
	
	@Override
	public void delete(String mid) throws SQLException {
		mapper.delete(mid);
	}
}
