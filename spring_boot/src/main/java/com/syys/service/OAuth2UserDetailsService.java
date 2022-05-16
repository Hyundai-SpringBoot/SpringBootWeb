package com.syys.service;

import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        // 기본에 동일한 이메일로 가입한 회원인지 확인
        Member2 result = memberRepository.findById(mid, 1);
        log.info("result : "+ result);
        // 기본 회원이면 정보 반환
        if (!(result == null)) {
            log.info("기존 회원");
            return  result;
        } // end if
 
        // 가입한적이 없다면 추가 패스워드 1111 이름은 이메일주소
        Member member = new Member();
        member.setMid(mid);
        
        member.setMpassword(passwordEncoder.encode("1111"));
        member.setSocial(1);
        // 디비에 ClubMember 행저장
        memberRepository.insertMember(member);
 
        RoleSet role = new RoleSet();
        role.setMid(mid);
        role.setRole_set(MemberRole.USER.toString());
        // 디비에 ClubRoleSet 행저장
        memberRepository.insertRoleSet(role);
 
        result = memberRepository.findById(mid, 1);
        // 추가된 정보 반환
        return result;
    }// end saveSocialMember..

   @Override
   public OAuth2User loadUser(OAuth2UserRequest userRequest)
           throws OAuth2AuthenticationException {
       log.info("-------loaduser-------------");
       log.info("userRequest" + userRequest);

       String clienName = userRequest.getClientRegistration().getClientName();
       // 인증 제공자 출력
       log.info("clienName" + clienName);
       log.info(userRequest.getAdditionalParameters());

       // 사용자 정보 가져오기 구글에서 허용한 API 범위
       OAuth2User oAuth2User = super.loadUser(userRequest);
       log.info("======oAuth2User===============");
       oAuth2User.getAttributes().forEach((k, v) -> {
           log.info(k + " : " + v);
       });// end foreach

       // 신규회원 테이블에 저장 시작
       String mid = null;
       if (clienName.equals("Google")) {// 구글 인증 확인
           mid = oAuth2User.getAttribute("email");
       } // end if
       log.info("구글 인증 확인");
       log.info("mid : " + mid);

       try {
           Member2 member2 = saveSocialMember(mid);
           log.info("---saveSocialMember--");
           log.info(member2);
       } catch (SQLException e) {
           log.info("saveSocialMember error");
           log.info("에러 ");
           log.info(e.toString());
       }//end try

       // 구글에서 정보 가져온 oAuth2User
       return oAuth2User;
   }// end load..

}// end class

