package com.syys.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BoardVO {
	private int bno;
	private String content;
	private String mid;
	private Date rdate;
	private Date udate;
	private MultipartFile img;
	private String imagefile;
	private String type;
}
