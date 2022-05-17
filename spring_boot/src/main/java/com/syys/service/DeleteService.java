package com.syys.service;
/*************************************************************
파일명: DeleteService.java
기능: 회원정보 삭제 Interface
작성자: 이승연
*************************************************************/
import java.sql.SQLException;

import com.syys.entity.Member;

public interface DeleteService {
	public void delete(String mid) throws SQLException;
}
