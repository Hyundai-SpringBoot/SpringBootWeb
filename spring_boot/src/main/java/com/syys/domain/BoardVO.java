package com.syys.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private int bno;
	private String content;
	private String mid;
	private Date rdate;
	private Date udate;
	private String imagefile;
}
