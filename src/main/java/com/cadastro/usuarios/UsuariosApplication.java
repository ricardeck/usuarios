package com.cadastro.usuarios;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class UsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuariosApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("1.0.0") String appVersion) {
		return new OpenAPI()
				.info(new Info().title("Cadastro de Usuários")
						.contact(new Contact().name("Ricardo Loureiro").email("rcvloureiro@gmail.com"))
						.description("API Java com Spring para cadastro de usuários.").version("v1.0.0"))
				.externalDocs(new ExternalDocumentation().description("Github").url("https://github.com/ricardeck/cadastroDeUsuarios"));
	}

}
