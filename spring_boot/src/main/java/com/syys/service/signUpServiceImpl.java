package com.syys.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.syys.entity.Member2;
import com.syys.entity.RoleSet;
import com.syys.mapper.MemberRepository;
@Service
public class signUpServiceImpl implements signUpService{
	@Autowired
	private MemberRepository mapper;
	
	@Autowired
	private PasswordEncoder passwordencoder; 
	
	public boolean insertMember(Member2 member2) throws SQLException {
		if(mapper.findById(member2.getMid(), 0)!=null) {
			return false;
		}
		member2.setMpassword(passwordencoder.encode(member2.getMpassword()));
		mapper.insertMember(member2);
		RoleSet roleset = new RoleSet(member2.getMid(),"USER");
		mapper.insertRoleSet(roleset);
		return true;
	}
	
}
