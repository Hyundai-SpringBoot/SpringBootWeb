/*************************************************************
파일명: SecurityConfig.java
기능: Security 를 사용하기 위한 로그인 설정
작성자: 이승연

[코멘트: 일반 사용자 로그인, 소셜로그인 그리고 접근제한을 설정할 수 있다.]
*************************************************************/
package com.syys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



import lombok.extern.log4j.Log4j2;

@Configuration
@EnableWebSecurity
@Log4j2
//Security 설정
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
   @Bean // Bean 등록
   PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {

      http.authorizeRequests()
      .antMatchers("/login/login").permitAll() //login.html은 모든 사용자가 볼 수 있다.
      .antMatchers("/board").hasRole("USER"); //게시판은 USER 권한만 가진 사용자가 볼 수 있다.
      
      
      //일반 사용자 로그인
      http.formLogin().loginPage("/login/login")
                  .loginProcessingUrl("/login")
                  .usernameParameter("mid") //DB의 Member 테이블과 연동하기 위해 username을 mid로 설정한다.
                  .passwordParameter("mpassword")//DB의 Member 테이블과 연동하기 위해 username을 mpassword로 설정한다.
                  .defaultSuccessUrl("/board")//로그인 성공 후 이동할 URL
                  .failureUrl("/login/login?error=true"); //로그인 실패시 URL
      
      
      //소셜 로그인 (구글)
      http.oauth2Login().defaultSuccessUrl("/board");
      
      
      // csrf 토큰 비활성화                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
      http.csrf().disable(); 
      
      
      //로그아웃
      http.logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login/login"); 

   }

}