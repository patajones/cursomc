package br.com.patajones.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.patajones.cursomc.domain.Categoria;
import br.com.patajones.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public void run(String... arg0) throws Exception {
		categoriaRepository.save(Arrays.asList(new Categoria(null, "Informática"), new Categoria(null, "Escritório")));
	}

}
