package com.syys.entity;
/*************************************************************
파일명: Member2.java
기능: 두 개의 테이블을 조인 한 후의 결과를 받는 VO
작성자: 이승연
*************************************************************/
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member2 {
	private String mid;
	private String mpassword;
	private String mname;
	private String memail;
	private int social;
	private Date regdate, moddate;
	private String role_set;
	

}
