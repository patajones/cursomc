package br.com.patajones.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.patajones.cursomc.domain.Cliente;
import br.com.patajones.cursomc.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message="Nome deve ser informado.")
	@Length(min=5, max=80, message="O nome deve conter entre 5 e 80 carateres.")
	private String nome;
	
	@NotEmpty(message="Email deve ser informado.")
	@Email(message="Email inválido.")
	private String email;	

	public ClienteDTO() {

	}

	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.setEmail(cliente.getEmail());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
