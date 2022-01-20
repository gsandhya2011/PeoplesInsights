package com.example.peoplesinsights.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

//this is a wrapper around @AuthenticationPrincipal annotation
//this is a meta annotation and reduces the dependency on spring security
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal //spring security provided annotation to access currently authenticated user in the controllers
public @interface CurrentUser {

}
