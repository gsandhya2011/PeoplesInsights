package com.example.peoplesinsights.security;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;



//this provides implementation for commence method of AuthenticationEntryPoint.
//this method is called whenever an exception is thrown due to an unauthorized user trying to access a resource  that requires authentication
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {
		
		logger.error("Responding with Unauthorized error. Message - {}", e.getMessage());
		httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
		
	}

}
