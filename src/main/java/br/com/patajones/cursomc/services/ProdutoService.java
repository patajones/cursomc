package br.com.patajones.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.patajones.cursomc.domain.Categoria;
import br.com.patajones.cursomc.domain.Produto;
import br.com.patajones.cursomc.repositories.CategoriaRepository;
import br.com.patajones.cursomc.repositories.ProdutoRepository;
import br.com.patajones.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	@Autowired
	private CategoriaRepository repo_categoria;

	public Produto find(Integer id) {
		Produto obj = repo.findOne(id);
		if (obj==null) {
			throw new ObjectNotFoundException("Produto não encontrado! Id:"+id);
		}
		return obj;
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = repo_categoria.findAll(ids); 
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	
	}	
}
