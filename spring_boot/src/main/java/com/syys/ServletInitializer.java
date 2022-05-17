/*************************************************************
파일명: ServletInitializeer.java
기능: Springboot servlet 초기화
작성자: 진영서

[코멘트: x]
*************************************************************/
package com.syys;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpBootRestOracleMybatisThymeMavenApplication.class);
	}

}
