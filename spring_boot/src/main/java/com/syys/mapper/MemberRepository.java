package com.syys.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.syys.entity.Member;
import com.syys.entity.Member2;
import com.syys.entity.RoleSet;

@Mapper
public interface MemberRepository {
	public void insertMember(Member member) throws SQLException;
	public void insertRoleSet(RoleSet roleSet) throws SQLException;
	public Member2 findById(@Param("mid") String mid, @Param("social") int social) throws SQLException;
}
