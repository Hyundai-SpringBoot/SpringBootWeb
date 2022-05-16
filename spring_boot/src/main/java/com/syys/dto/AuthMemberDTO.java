package com.syys.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.io.Serializable;
import lombok.extern.log4j.Log4j2;
@Getter
@Setter
@ToString
public class AuthMemberDTO extends User{
	private static final long serialVersionUID=1L;
	private String mid;
	private String mpassword;
	private String mname;
	private String memail;
	private int social;
	
	public AuthMemberDTO(String mid, String password,String mname,String memail,int social,
						 List<GrantedAuthority> authorities) {
		super(mid,password,authorities);
		this.memail=memail;
		this.social=social;
		this.mname=mname;
	}
}
