package com.syys.securityhandler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.syys.dto.AuthMemberDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	public LoginSuccessHandler(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
 
    // RedirectStrategy 인터페이스 생성 sendRedirect() 메서드 이용
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 
    // 사용자 암호 확인 용
    private PasswordEncoder passwordEncoder;

	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                       HttpServletResponse response,
                                       Authentication authentication)
           throws IOException, ServletException {
       log.info("------------------------");
       log.info("onAuthenticationSuccess");
       
    // 인증 객체에서 사용자 정보 저장
       AuthMemberDTO authMemberDTO = (AuthMemberDTO) authentication.getPrincipal();
       log.info(authMemberDTO);
       // 소셜 사용자인지 확인
       int Social = authMemberDTO.getSocial();
       log.info("Social"+Social);
       

   }//end onAu…

}//end class

