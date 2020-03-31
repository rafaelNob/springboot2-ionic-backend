package com.nelioalves.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.nelioalves.cursomc.domain.Pedido;

public interface EmailService {
	void sendOrderConfirmationEmail(Pedido p);
	
	void sendEmail(SimpleMailMessage simpleMailMessage);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	//MimeMessage PARA ENVIAR EMAIL HTML
	void sendHtmlEmail(MimeMessage msg);

}
