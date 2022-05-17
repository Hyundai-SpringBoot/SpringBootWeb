package com.syys.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.syys.entity.Member;
import com.syys.mapper.MemberRepository;

@Service
public class UpdateServiceImpl implements UpdateService {
	@Autowired
	private MemberRepository mapper;
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	@Override
	public void updateMember(Member member) throws SQLException{
		member.setMpassword(passwordencoder.encode(member.getMpassword()));
		mapper.updateMember(member);
		
		
		
	}

	
}
