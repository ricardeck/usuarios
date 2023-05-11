package com.cadastro.usuarios.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cadastro.usuarios.domains.Usuario;
import com.cadastro.usuarios.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;

	@Operation(summary = "save", description = "Salva um usuario", tags = { "usuarios" })
	@PostMapping("/usuarios")
	public ResponseEntity<?> save(@RequestBody Usuario usuario) {
		usuario = service.save(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();
		return ResponseEntity.created(uri).body(usuario);
	}

	@Operation(summary = "update", description = "Atualiza um usuario", tags = { "usuarios" })
	@PutMapping("/usuarios")
	public ResponseEntity<?> update(@RequestBody Usuario usuario) {
		usuario = service.update(usuario);
		return ResponseEntity.ok().body(usuario);
	}

	@Operation(summary = "deleteById", description = "Deleta um usuario por id", tags = { "usuarios" })
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "findById", description = "Retorna um usuario por id", tags = { "usuarios" })
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Usuario usuario = service.findById(id);
		return ResponseEntity.ok().body(usuario);
	}

	@Operation(summary = "findByNome", description = "Retorna todos os usuarios por nome", tags = { "usuarios" })
	@GetMapping("/usuarios/nome/{nome}")
	public ResponseEntity<?> findByNome(@PathVariable String nome) {
		List<Usuario> usuarios = service.findByNome(nome);
		return ResponseEntity.ok().body(usuarios);
	}

	@Operation(summary = "findByEmail", description = "Retorna todos os usuarios por email", tags = { "usuarios" })
	@GetMapping("/usuarios/email/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email) {
		List<Usuario> usuarios = service.findByNome(email);
		return ResponseEntity.ok().body(usuarios);
	}

	@Operation(summary = "findAll", description = "Retorna todos os usuarios", tags = { "usuarios" })
	@GetMapping("/usuarios")
	public ResponseEntity<?> findAll() {
		List<Usuario> usuarios = service.findAll();
		return ResponseEntity.ok().body(usuarios);
	}

	@Operation(summary = "findAllPage", description = "Retorna todos os usuarios paginadas", tags = { "usuarios" })
	@GetMapping("/usuarios/page")
	public ResponseEntity<Page<Usuario>> findAllPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "2") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {

		Page<Usuario> usuarios = service.findAllPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(usuarios);
	}

}
