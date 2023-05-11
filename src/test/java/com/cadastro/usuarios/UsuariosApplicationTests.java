package com.cadastro.usuarios;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cadastro.usuarios.resources.UsuarioResource;
import com.cadastro.usuarios.services.UsuarioService;

@SpringBootTest
class UsuariosApplicationTests {

	@Autowired
	private UsuarioResource resource;

	@Autowired
	private UsuarioService service;

	@Test
	public void contextLoads() throws Exception {
		assertThat(resource).isNotNull();
		assertThat(service).isNotNull();
	}

}
