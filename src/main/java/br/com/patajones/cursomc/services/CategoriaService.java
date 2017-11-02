package br.com.patajones.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patajones.cursomc.domain.Categoria;
import br.com.patajones.cursomc.repositories.CategoriaRepository;
import br.com.patajones.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		if (obj==null) {
			throw new ObjectNotFoundException("Categoria não encontrada! Id:"+id);
		}
		return obj;
	}
}
