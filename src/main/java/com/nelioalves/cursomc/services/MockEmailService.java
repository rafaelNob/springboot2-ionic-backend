package com.nelioalves.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService{
	
	private static Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	@Override
	public void sendEmail(SimpleMailMessage sm) {
		
		LOG.info("Simulando Envio de Email...");
		LOG.info(sm.toString());
		LOG.info("Email Enviado!");
		
	}
	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulando Envio de HTml...");
		LOG.info(msg.toString());
		LOG.info("Email Enviado!");
		
	}
	
	

}
