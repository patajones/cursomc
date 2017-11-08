package br.com.patajones.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.patajones.cursomc.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantianteDatabase() throws ParseException {
		if ("create".equals(strategy) || "create-drop".equals(strategy)) {
			dbService.instantiateTestDatabase();
			return true;
		} else {
			return false;
		}
		
	}
}
