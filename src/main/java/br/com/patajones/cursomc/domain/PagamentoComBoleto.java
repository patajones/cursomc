package br.com.patajones.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.patajones.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento {
	
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataVencimento;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataPagamento;
	
	public PagamentoComBoleto() {
	
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento; 
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
}
