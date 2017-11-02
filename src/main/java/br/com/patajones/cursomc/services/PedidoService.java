package br.com.patajones.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patajones.cursomc.domain.Pedido;
import br.com.patajones.cursomc.repositories.PedidoRepository;
import br.com.patajones.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido buscar(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj==null) {
			throw new ObjectNotFoundException("Pedido não encontrado! Id:"+id);
		}
		return obj;
	}
}
