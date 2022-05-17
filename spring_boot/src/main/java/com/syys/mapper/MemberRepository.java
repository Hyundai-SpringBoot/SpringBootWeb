package com.syys.mapper;
/*************************************************************
파일명: MemberRepository.java
기능: Member Mapper의 역할을 담당한다.
작성자: 이승연
*************************************************************/
import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.syys.entity.Member;
import com.syys.entity.Member2;
import com.syys.entity.RoleSet;

@Mapper
public interface MemberRepository {
	public void insertMember(Member2 member2) throws SQLException; //DB에 회원정보 삽입
	public void insertRoleSet(RoleSet roleSet) throws SQLException;//DB에 회원 권한 삽입
	public Member2 findById(@Param("mid") String mid, @Param("social") int social) throws SQLException; //mid, social을 기준으로 조인한 테이블에서 ID 찾기
	public void updateMember(Member member) throws SQLException; //회원정보 업데이트
	public void delete(String mid) throws SQLException; //회원정보 삭제
}
