package com.syys.service;
/*************************************************************
파일명: UserDetailService.java
기능: 일반 사용자 로그인 서비스 (소셜로그인 제외)
작성자: 이승연
*************************************************************/

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import com.syys.dto.AuthMemberDTO;
import com.syys.entity.Member2;
import com.syys.mapper.MemberRepository;



@Service
@Log4j2
public class UserDetailService implements UserDetailsService{
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException{
		log.info("userDetailsService...............");
		Member2 result=null;
		try {
			//result에는 mid에 해당하는 데이터를 반환한다.
			log.info(mid); 
			result=memberRepository.findById(mid,0); 
		}catch(SQLException e) {
			throw new UsernameNotFoundException("다시 한번 확인해주세요.");
		}//end try
		log.info(result);
		log.info(result.getRole_set().toString());
		
		//authorities에 권한을 add 한다.
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_"+result.getRole_set()));
		
		AuthMemberDTO authMemberDTO = new AuthMemberDTO(result.getMid(), result.getMpassword(),result.getMname(),result.getMemail(),
														result.getSocial(), authorities);
		authMemberDTO.setMid(result.getMid());
		authMemberDTO.setMname(result.getMname());
		authMemberDTO.setSocial(result.getSocial());
		authMemberDTO.setMpassword(result.getMpassword());
	
		log.info("authMemberDTO : "+authMemberDTO);
		log.info(authMemberDTO.getAuthorities().toString());
		return authMemberDTO;
	}

}
