package br.com.patajones.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.patajones.cursomc.domain.Cliente;
import br.com.patajones.cursomc.dto.ClienteDTO;
import br.com.patajones.cursomc.repositories.ClienteRepository;
import br.com.patajones.cursomc.services.exceptions.DataIntegrityException;
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

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Cliente update(Cliente obj) {
		Cliente data = find(obj.getId());
		updateData(data, obj);		
		return repo.save(data);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir o Cliente pois a outros dados associados à ele.");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(),null);
	}
	
	public void updateData(Cliente data, Cliente obj) {		
		data.setNome(obj.getNome());
		data.setEmail(obj.getEmail());		
	}	
}
