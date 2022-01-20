package com.example.peoplesinsights.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.peoplesinsights.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

//this is custom UserDetails class
// this is the class whose instances will be returned from our custom UserDetailsService
//Spring Security uses information stored in UserPrincipal object to perform authentication and authorization
public class UserPrincipal implements UserDetails{
	private Long id;
	private String name;
	private String username;
	
	@JsonIgnore
	private String password;
	
	@JsonIgnore
	private String email;	
	
	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(Long id, String name, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
	}
	
	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
					new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

        System.out.println("enter into create method of user principle");
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getRoles());
        System.out.println(authorities.toString());
		return new 
				UserPrincipal(
						user.getId(), 
						user.getName(), 
						user.getUsername(), 
						user.getEmail(), 
						user.getPassword(), 
						authorities);
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;	
	}
	
	public String getEmail() {
		return email;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		System.out.println("equals method");
		System.out.println(o);
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		UserPrincipal that = (UserPrincipal) o;
		System.out.println(id);
		System.out.println(that.id);
		return Objects.equals(id, that.id);
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
