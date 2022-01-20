package com.example.peoplesinsights.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//In order to access API's from react client that runs on its own deployment server.
// this is to allow cross origin requests from react client
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	private final long MAX_AGE_SECS = 3600;
	
	@Value("${app.cors.allowedOrigins}")
	private String[] allowedOrigins;
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins(allowedOrigins)
			.allowedMethods("HEAD", "OPTIONS", "GET", "PUT", "POST", "PATCH", "DELETE")
			.maxAge(MAX_AGE_SECS);
		
	}

}
