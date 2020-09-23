package com.logistic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.logistic.services.IEmailService;
import com.logistic.services.imp.MockEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Bean
	public IEmailService emailService() {
		return new MockEmailService();
	}
	
}
