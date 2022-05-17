package com.syys.dto;
/*************************************************************
파일명: AuthMemberDTO.java
기능: 멤버에 권한 설정하는 DTO
작성자: 이승연
*************************************************************/
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.io.Serializable;
import lombok.extern.log4j.Log4j2;
@Getter
@Setter
@ToString
public class AuthMemberDTO extends User implements OAuth2User{
	private static final long serialVersionUID=1L;
	private String mid;
	private String mpassword;
	private String mname;
	private String memail;
	private int social;
	

    Map<String, Object> OA2Attributes;
	public AuthMemberDTO(String mid, String password,String mname,String memail,int social,
						 List<GrantedAuthority> authorities) {
		super(mid,password,authorities);
		this.memail=memail;
		this.social=social;
		this.mname=mname;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return OA2Attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return mname;
	}
}
