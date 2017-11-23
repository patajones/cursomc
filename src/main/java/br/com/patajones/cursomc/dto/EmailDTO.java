package br.com.patajones.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class EmailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Email obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	public EmailDTO() {	
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
