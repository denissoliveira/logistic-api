package com.logistic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.logistic.services.IEmailService;
import com.logistic.services.imp.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Bean
	public IEmailService emailService() {
		return new MockEmailService();
	}
	
}
