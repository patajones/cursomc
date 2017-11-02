package br.com.patajones.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patajones.cursomc.domain.Cliente;
import br.com.patajones.cursomc.repositories.ClienteRepository;
import br.com.patajones.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if (obj==null) {
			throw new ObjectNotFoundException("Cliente não encontrado! Id:"+id);
		}
		return obj;
	}
}
