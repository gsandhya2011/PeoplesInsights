package com.example.peoplesinsights.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.peoplesinsights.security.CustomUserDetailsService;
import com.example.peoplesinsights.security.JwtAuthenticationEntryPoint;
import com.example.peoplesinsights.security.JwtAuthenticationFilter;

 //used to enable web security in the project
//method level security to protect controller/service methods based on annotations -@Secured("ROLE_ADMIN")
//jsr250Enables - @RolesAllowed("ROLE_ADMIN"), prePostEnabled - @PreAuthorize(), @PostAuthorize() - enables more complex expression
//based access control syntax.
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{ 
	
//our class extends WebSecurityConfigurerAdapter and overrides its methods to provide custom security configurations	
	
	@Autowired //to authenticate a user or perform role based checks, spring security need to load users details
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired //return 401 unauthorized error to clients who try to access a protected resource without proper Authentication
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
 // this implements a filter that 1.reads JWT authentication token from authorization header of all requests.
	//2.validates the token 3.loads user details associated with the token 4.sets user details in spring security's security context
	//3.Spring security uses the user details to perform authorization checks, 4. also access user details form security context in  
	//controller to perform our business logic
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	//used to create AuthenticationManager instance which is main spring security interface for authenticating a user.
	//used to build in-memory authentication, LDAP authentication, JDBC authentication or custom authentication provider.	
	//here we provided customUserDetailsService and passwordEncoder to build AuthenticationManager.
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(customUserDetailsService)
		.passwordEncoder(passwordEncoder());
	}
	
	//use the configured AuthenticationManager to authenticate a user in the login API
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//to configure security functionalities like csrf, sessionManagement, and add rules to protect resources based on various conditions
	//permit access to static resources and few public API's to everyone and restrict access to other API's to authenticated users only.
	//we also added JwtAuthenticationEntryPoint and custom JWTConfigurationFilter in HttpSecurity configuration.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and()
		.csrf().disable().exceptionHandling()
		.authenticationEntryPoint(unauthorizedHandler).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests()
		.antMatchers("/", "favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
		.antMatchers("/api/auth/**").permitAll()
		.antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability").permitAll()
		.antMatchers(HttpMethod.GET, "/api/polls/**", "/api/users/**").permitAll()
		.anyRequest().authenticated();
		
		
	//Add our custom JWT security filter
	http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
