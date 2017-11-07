package br.com.patajones.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patajones.cursomc.domain.ItemPedido;
import br.com.patajones.cursomc.domain.PagamentoComBoleto;
import br.com.patajones.cursomc.domain.Pedido;
import br.com.patajones.cursomc.domain.enums.EstadoPagamento;
import br.com.patajones.cursomc.repositories.ItemPedidoRepository;
import br.com.patajones.cursomc.repositories.PagamentoRepository;
import br.com.patajones.cursomc.repositories.PedidoRepository;
import br.com.patajones.cursomc.repositories.ProdutoRepository;
import br.com.patajones.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boleto_service;
	
	@Autowired
	private PagamentoRepository repo_pagamento;
	
	@Autowired
	private ProdutoRepository repo_produto;

	@Autowired
	private ItemPedidoRepository repo_itemPedido;

	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj==null) {
			throw new ObjectNotFoundException("Pedido não encontrado! Id:"+id);
		}
		return obj;
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();			
			boleto_service.preecherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		repo_pagamento.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(repo_produto.findOne(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		repo_itemPedido.save(obj.getItens());
		return obj;
	}
}
