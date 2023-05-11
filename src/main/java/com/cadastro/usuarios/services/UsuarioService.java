package com.cadastro.usuarios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cadastro.usuarios.domains.Usuario;
import com.cadastro.usuarios.exceptions.ObjectNotFoundException;
import com.cadastro.usuarios.repositories.UsuarioRepository;
import com.cadastro.usuarios.utils.Validations;

import jakarta.validation.ValidationException;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;

	public Usuario save(Usuario usuario) {
		if (!Validations.isValidEmail(usuario.getEmail())) {
			throw new ValidationException("Erro de validacao - Email inválido");
		} else if (!Validations.isValidNome(usuario.getNome())) {
			throw new ValidationException("Erro de validacao - Nome inválido");
		} else if (!Validations.isValidEndereco(usuario.getEndereco())) {
			throw new ValidationException("Erro de validacao - Endereço inválido");
		} else if (!Validations.isValidNascimento(usuario.getDataNascimento())) {
			throw new ValidationException("Erro de validacao - Data de nascimento não permitida");
		}
		return repository.save(usuario);
	}

	public Usuario update(Usuario usuario) {
		findById(usuario.getId());
		return repository.saveAndFlush(usuario);
	}

	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}

	public Usuario findById(Integer id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário com o id " + id + " não encontrado"));
	}

	public List<Usuario> findByNome(String nome) throws ObjectNotFoundException {
		List<Usuario> usuarios = repository.findByNomeContainingIgnoreCase(nome);
		return usuarios;
	}

	public List<Usuario> findByEmail(String nome) throws ObjectNotFoundException {
		List<Usuario> usuarios = repository.findByEmailContainingIgnoreCase(nome);
		return usuarios;
	}

	public List<Usuario> findAll() {
		return repository.findAll();
	}

	public Page<Usuario> findAllPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);

	}

}
