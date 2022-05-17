package com.syys.service;
/*************************************************************
파일명: OAuth2UserDetailsService.java
기능: 소셜 로그인 기능 구현
작성자: 이승연
*************************************************************/
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.syys.dto.AuthMemberDTO;
import com.syys.entity.Member;
import com.syys.entity.Member2;
import com.syys.entity.MemberRole;
import com.syys.entity.RoleSet;
import com.syys.mapper.MemberRepository;

@Log4j2
@Service
public class OAuth2UserDetailsService extends DefaultOAuth2UserService{
	@Autowired
    private MemberRepository memberRepository;
    // 패스워드 암호화
    @Autowired
    private  PasswordEncoder passwordEncoder;

	private Member2 saveSocialMember(String mid) throws SQLException {
        
		log.info("saveSocialMember  시작");
        // 이메일 중복확인
        Member2 result = memberRepository.findById(mid, 1);
        log.info("result : "+ result);
        // 기본 회원이면 해당 정보 반환
        if (!(result == null)) {
            log.info("기존 회원");
            return  result;
        }
 
        // 패스워드 1111 이름은 이메일주소
        Member2 member2 = new Member2();
        member2.setMid(mid);
        member2.setMname(mid);
        member2.setMpassword(passwordEncoder.encode("1111"));
        member2.setSocial(1);
        // Member2 저장
        memberRepository.insertMember(member2);
 
        RoleSet role = new RoleSet();
        role.setMid(mid);
        role.setRole_set(MemberRole.USER.toString());
        // RoleSet 저장
        memberRepository.insertRoleSet(role);
 
        result = memberRepository.findById(mid, 1);
        
        return result;
    }

   @Override
   public OAuth2User loadUser(OAuth2UserRequest userRequest)
           throws OAuth2AuthenticationException {
       log.info("-------loaduser-------------");
       log.info("userRequest" + userRequest);

       String clienName = userRequest.getClientRegistration().getClientName();
       // 인증  출력
       log.info("clienName" + clienName);
       log.info(userRequest.getAdditionalParameters());

       // 사용자 정보 가져오기 
       OAuth2User oAuth2User = super.loadUser(userRequest);
       log.info("======oAuth2User===============");
       oAuth2User.getAttributes().forEach((k, v) -> {
           log.info(k + " : " + v);
       });// end foreach

       // 신규회원 테이블에 저장 시작
       String mid = null;
       if (clienName.equals("Google")) {// 구글 인증 확인
           mid = oAuth2User.getAttribute("email");
       } // end 
       log.info("구글 인증 확인");
       log.info("mid : " + mid);
Member2 result = null;
       try {
    	   result = saveSocialMember(mid);
           log.info("---saveSocialMember--");
           log.info(result);
           List<GrantedAuthority> authorities = new ArrayList<>();
   			authorities.add(new SimpleGrantedAuthority("ROLE_"+result.getRole_set()));
           AuthMemberDTO authMemberDTO = new AuthMemberDTO(result.getMid(), result.getMpassword(),result.getMname(),result.getMemail(),
					result.getSocial(), authorities);
			authMemberDTO.setMid(result.getMid());
			authMemberDTO.setMname(result.getMname());
			authMemberDTO.setSocial(result.getSocial());
			authMemberDTO.setMpassword(result.getMpassword());
			log.info("authMemberDTO : "+authMemberDTO);
			log.info(authMemberDTO.getAuthorities().toString());
			return authMemberDTO;
       } catch (SQLException e) {
           log.info("saveSocialMember error");
           log.info("에러 ");
           log.info(e.toString());
       }//end 

       
       return oAuth2User;
   }

}// end 

