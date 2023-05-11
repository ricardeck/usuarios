package com.cadastro.usuarios.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cadastro.usuarios.domains.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public List<Usuario> findByNomeContainingIgnoreCase(String nome);

	public List<Usuario> findByEmailContainingIgnoreCase(String email);

}
