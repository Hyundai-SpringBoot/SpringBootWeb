package com.syys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.syys.securityhandler.LoginSuccessHandler;

import lombok.extern.log4j.Log4j2;

@Configuration
@EnableWebSecurity
@Log4j2

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
    public LoginSuccessHandler successHandler(){
        return new LoginSuccessHandler(passwordEncoder());
    }//end CLu..   

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login/login.html")
						.loginProcessingUrl("/login")
						.usernameParameter("mid")
						.passwordParameter("mpassword")
						.defaultSuccessUrl("/board")
						.failureUrl("/login/login.html?error=true");
		
		http.oauth2Login().defaultSuccessUrl("/board");
																																																																																																																																																																																																																																																										
		http.csrf().disable(); // csrf 토큰 비활성화
		http.logout();

	}

}
