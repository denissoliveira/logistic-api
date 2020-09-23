package com.logistic.services.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/*
 * Usado para "mockar" o envio de email no profile de dev
 */
public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de email...");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
	}
}
