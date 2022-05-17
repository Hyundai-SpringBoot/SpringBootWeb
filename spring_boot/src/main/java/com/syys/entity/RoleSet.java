package com.syys.entity;
/*************************************************************
파일명: RoleSet.java
기능: RoleSet 테이블 과 같은 VO
작성자: 이승연
*************************************************************/
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleSet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mid;
	private String role_set;
}
