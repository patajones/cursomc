package br.com.patajones.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.patajones.cursomc.domain.Cliente;
import br.com.patajones.cursomc.domain.Endereco;
import br.com.patajones.cursomc.domain.enums.Perfil;
import br.com.patajones.cursomc.domain.enums.TipoCliente;
import br.com.patajones.cursomc.dto.ClienteDTO;
import br.com.patajones.cursomc.dto.ClienteNewDTO;
import br.com.patajones.cursomc.repositories.CidadeRepository;
import br.com.patajones.cursomc.repositories.ClienteRepository;
import br.com.patajones.cursomc.repositories.EnderecoRepository;
import br.com.patajones.cursomc.security.UserSS;
import br.com.patajones.cursomc.services.exceptions.AuthorizationException;
import br.com.patajones.cursomc.services.exceptions.DataIntegrityException;
import br.com.patajones.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	@Autowired
	private CidadeRepository repo_cidade;
	@Autowired
	private EnderecoRepository repo_endereco;	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Cliente find(Integer id) {
		UserSS user = UserService.authenticated();
		if (user==null || (!user.hasRole(Perfil.ADMIN) && !id.equals(user.getId()))) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Cliente obj = repo.findOne(id);
		if (obj==null) {
			throw new ObjectNotFoundException("Cliente não encontrado! Id:"+id);
		}
		return obj;
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		repo_endereco.save(obj.getEnderecos());
		return obj;
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
			throw new DataIntegrityException("Não é possível excluir o Cliente pois a pedidos associados à ele.");
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
	
	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente obj = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), encoder.encode(dto.getSenha()), TipoCliente.toEnum(dto.getTipo()));
		Endereco end = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), obj, repo_cidade.findOne(dto.getCidadeId()));
		obj.getEnderecos().add(end);
		obj.getTelefones().add(dto.getTelefone1());
		if (dto.getTelefone2()!=null) { 
			obj.getTelefones().add(dto.getTelefone2());
		}
		if (dto.getTelefone3()!=null) { 
			obj.getTelefones().add(dto.getTelefone3());
		}		
		return obj;
	}
	
	public void updateData(Cliente data, Cliente obj) {		
		data.setNome(obj.getNome());
		data.setEmail(obj.getEmail());		
	}
}
