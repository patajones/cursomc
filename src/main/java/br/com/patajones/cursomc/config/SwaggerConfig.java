package br.com.patajones.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.host("localhost:8080")				
				.apiInfo(metaData())
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.patajones.cursomc.resources"))
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfoBuilder()
				.title("SISTEMA DE TESTE DO RICARDO")
				.description("Sistema que só serviu para estudos do Spring boot.")
				.version("1.0")
				.contact(new Contact("patajones","https://github.com/patajones","ricardo.bernardes@gmail.com"))
				.build();
        return apiInfo;
    }	
	
}
