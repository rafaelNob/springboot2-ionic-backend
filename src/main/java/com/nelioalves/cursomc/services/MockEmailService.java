package com.nelioalves.cursomc.services;

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
	
	

}
