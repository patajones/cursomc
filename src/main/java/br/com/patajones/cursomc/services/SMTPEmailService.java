package br.com.patajones.cursomc.services;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SMTPEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;

	private static final Logger LOG = LoggerFactory.getLogger(SMTPEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		String to = Arrays.toString(msg.getTo());
		LOG.debug("Enviando email para " + to);
		LOG.debug(msg.toString());
		mailSender.send(msg);
		LOG.info("Email enviado para " + to);
	}

}
