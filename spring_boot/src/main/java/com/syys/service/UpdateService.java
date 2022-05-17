package com.syys.service;
/*************************************************************
파일명: UpdateService.java
기능: 수정 서비스 인터페이스
작성자: 이승연
*************************************************************/
import java.sql.SQLException;

import com.syys.entity.Member;

public interface UpdateService {
	public void updateMember(Member member) throws SQLException;
}
