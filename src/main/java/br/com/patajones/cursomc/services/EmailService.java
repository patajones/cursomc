package br.com.patajones.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.patajones.cursomc.domain.Cliente;
import br.com.patajones.cursomc.domain.Pedido;

//Aplicando Pattern Strategy com interface
public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);
	
}
