package com.nelioalves.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService	 {
	
	private static Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	@Autowired
	private MailSender  mailSender;
	
	@Autowired
	private JavaMailSender  javaMailSender;
	
	@Override
	public void sendEmail(SimpleMailMessage sm) {
		// TODO Auto-generated method stub

		LOG.info("Simulando Envio de Email...");
		mailSender.send(sm);
		LOG.info("Email Enviado!");
		
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulando Envio de Email...");
		javaMailSender.send(msg);
		LOG.info("Email Enviado!");
		
	}


}
