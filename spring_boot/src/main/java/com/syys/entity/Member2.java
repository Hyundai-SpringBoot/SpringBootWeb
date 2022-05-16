package com.syys.entity;
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
