package com.syys.entity;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Repository;

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
public class Member implements Serializable{
	private String mid;
	private String mpassword;
	private String mname;
	private String memail;
	private int social;
	private Date regdate, moddate;

}
