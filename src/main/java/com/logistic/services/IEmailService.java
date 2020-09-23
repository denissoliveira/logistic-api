package com.logistic.services;

import org.springframework.mail.SimpleMailMessage;

import com.logistic.domain.Pedido;

public interface IEmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	//void sendNewPasswordEmail(Cliente cliente, String newPass);
}
