package com.logistic.services.imp;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/*
 * Usado para "mockar" o envio de email no profile de dev
 */
public class MockEmailService extends AbstractEmailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		logger.info("Simulando envio de email...");
		logger.info(msg.toString());
		logger.info("Email enviado");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		logger.info("Simulando envio de email...");
		logger.info(msg.toString());
		logger.info("Email enviado");
	}
}
