package com.example.peoplesinsights;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
//@EntityScan is used when entity classes are not placed in the main application package,
//	this tells spring where to find entities used in our application
@EntityScan(basePackageClasses = {
		PeoplesinsightsApplication.class,
		Jsr310JpaConverters.class
})
public class PeoplesinsightsApplication {
	
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(PeoplesinsightsApplication.class, args);
	}

}
