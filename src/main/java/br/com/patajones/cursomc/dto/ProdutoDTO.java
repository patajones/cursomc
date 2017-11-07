package br.com.patajones.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.patajones.cursomc.domain.Produto;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Nome deve ser informado.")
	private String nome;
	@NotNull(message="Preço deve ser informado.")
	private Double preco;	

	public ProdutoDTO() {

	}	
	
	public ProdutoDTO(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public ProdutoDTO(Produto obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.preco = obj.getPreco();
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
}
