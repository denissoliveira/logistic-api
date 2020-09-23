package com.logistic.services.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	//Aqui ele já pega sa informações do properties.yml
	@Autowired
	private MailSender mailSender;
	
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		logger.info("Enviando email...");
		mailSender.send(msg);
		logger.info("Email enviado");
	}
}
