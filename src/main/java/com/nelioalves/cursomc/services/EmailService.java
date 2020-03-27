package com.nelioalves.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.nelioalves.cursomc.domain.Pedido;

public interface EmailService {
	void sendOrderConfirmationEmail(Pedido p);
	
	void sendEmail(SimpleMailMessage simpleMailMessage);

}
