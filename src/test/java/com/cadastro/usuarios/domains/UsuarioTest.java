package com.cadastro.usuarios.domains;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.cadastro.usuarios.repositories.UsuarioRepository;
import com.cadastro.usuarios.services.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.validation.ValidationException;;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioServiceTest {

	@Mock
	private UsuarioRepository repository;

	@InjectMocks
	private UsuarioService service;

	@Test
	void createdSuccessTest() throws JsonProcessingException, Exception {
		LocalDate dataNascimento = LocalDate.ofYearDay(2005, 1);
		List<String> habilidades = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;

			{
				add("Java");
				add("C");
				add("Angular");
			}
		};

		Usuario usuario = new Usuario(1, "Nome Teste", "email@test.com", dataNascimento, "Rua do teste, 250",
				habilidades);

		when(repository.save(usuario)).thenReturn(usuario);

		Usuario usuarioReturn = service.save(usuario);
		Assertions.assertEquals(usuarioReturn.getNome(), "Nome Teste");
	}

	@Test
	void updatedSuccessTest() throws JsonProcessingException, Exception {
		Integer id = 1;
		LocalDate dataNascimento = LocalDate.ofYearDay(2005, 1);
		List<String> habilidades = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;

			{
				add("Java");
				add("C");
				add("Angular");
			}
		};

		Usuario usuario = new Usuario(1, "Nome Teste alterado", "email@test.com", dataNascimento, "Rua do teste, 250",
				habilidades);

		Optional<Usuario> usuario2 = Optional.ofNullable(new Usuario(1, "Nome Teste alterado", "email@test.com",
				dataNascimento, "Rua do teste, 250", habilidades));

		when(repository.saveAndFlush(usuario)).thenReturn(usuario);
		when(repository.findById(id)).thenReturn(usuario2);

		Usuario usuarioReturn = service.update(usuario);
		Assertions.assertEquals(usuarioReturn.getNome(), "Nome Teste alterado");
	}

	@Test
	void createdFaillDataNascimentoMenorQueDezoitoTest() throws JsonProcessingException, Exception {
		LocalDate dataNascimento = LocalDate.ofYearDay(2006, 1);
		List<String> habilidades = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;

			{
				add("Java");
				add("C");
				add("Angular");
			}
		};

		Usuario usuario = new Usuario(1, "Nome Teste", "email@test.com", dataNascimento, "Rua do teste, 250",
				habilidades);

		Throwable exception = Assertions.assertThrowsExactly(ValidationException.class, () -> service.save(usuario));
		Assertions.assertEquals("Erro de validacao - Data de nascimento não permitida", exception.getMessage());
	}

	@Test
	void createdFaillNomeComSimboloNaoPermitidoTest() throws JsonProcessingException, Exception {
		LocalDate dataNascimento = LocalDate.ofYearDay(2003, 1);
		List<String> habilidades = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;

			{
				add("Java");
				add("C");
				add("Angular");
			}
		};

		Usuario usuario = new Usuario(1, "Nome Teste @", "email@test.com", dataNascimento, "Rua do teste, 250",
				habilidades);

		Throwable exception = Assertions.assertThrowsExactly(ValidationException.class, () -> service.save(usuario));
		Assertions.assertEquals("Erro de validacao - Nome inválido", exception.getMessage());

	}

	@Test
	void createdFaillEmailInvalidoTest() throws JsonProcessingException, Exception {
		LocalDate dataNascimento = LocalDate.ofYearDay(2006, 1);
		List<String> habilidades = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;

			{
				add("Java");
				add("C");
				add("Angular");
			}
		};

		Usuario usuario = new Usuario(1, "Nome Teste", "emailtest.com", dataNascimento, "Rua do teste, 250",
				habilidades);

		Throwable exception = Assertions.assertThrowsExactly(ValidationException.class, () -> service.save(usuario));
		Assertions.assertEquals("Erro de validacao - Email inválido", exception.getMessage());

	}

	@Test
	void createdFaillEnderecoComCaracteresEspeciaisTest() throws JsonProcessingException, Exception {
		LocalDate dataNascimento = LocalDate.ofYearDay(2006, 1);
		List<String> habilidades = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;

			{
				add("Java");
				add("C");
				add("Angular");
			}
		};

		Usuario usuario = new Usuario(1, "Nome Teste", "email@test.com", dataNascimento, "Rua do teste!, 250",
				habilidades);

		Throwable exception = Assertions.assertThrowsExactly(ValidationException.class, () -> service.save(usuario));
		Assertions.assertEquals("Erro de validacao - Endereço inválido", exception.getMessage());
	}

	@Test
	void findAllTest() throws JsonProcessingException, Exception {
		LocalDate dataNascimento = LocalDate.ofYearDay(2005, 1);
		List<String> habilidades = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;

			{
				add("Java");
				add("C");
				add("Angular");
			}
		};

		Usuario usuario = new Usuario(1, "Nome Teste", "email@test.com", dataNascimento, "Rua do teste, 250",
				habilidades);

		Usuario usuario2 = new Usuario(2, "Nome2 Teste", "email2@test.com", dataNascimento, "Rua do teste2, 251",
				habilidades);

		List<Usuario> usuarios = new ArrayList<Usuario>() {
			private static final long serialVersionUID = 1L;

			{
				add(usuario);
				add(usuario2);
			}
		};

		when(repository.findAll()).thenReturn(usuarios);

		List<Usuario> usuariosReturn = service.findAll();
		Assertions.assertEquals(usuariosReturn.contains(usuario), true);
	}

	@Test
	void findByEmailTest() throws JsonProcessingException, Exception {
		String email = "email@test.com";
		LocalDate dataNascimento = LocalDate.ofYearDay(2005, 1);
		List<String> habilidades = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;

			{
				add("Java");
				add("C");
				add("Angular");
			}
		};

		Usuario usuario = new Usuario(1, "Nome Teste", "email@test.com", dataNascimento, "Rua do teste, 250",
				habilidades);

		Usuario usuario2 = new Usuario(2, "Nome2 Teste", "email@test.com.br", dataNascimento, "Rua do teste2, 251",
				habilidades);

		List<Usuario> usuarios = new ArrayList<Usuario>() {
			private static final long serialVersionUID = 1L;

			{
				add(usuario);
				add(usuario2);
			}
		};

		when(repository.findByEmailContainingIgnoreCase(email)).thenReturn(usuarios);

		List<Usuario> usuariosReturn = service.findByEmail(email);
		Assertions.assertEquals(usuariosReturn.contains(usuario), true);
	}

	@Test
	void findByNomeTest() throws JsonProcessingException, Exception {
		String nome = "teste";
		LocalDate dataNascimento = LocalDate.ofYearDay(2005, 1);
		List<String> habilidades = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;

			{
				add("Java");
				add("C");
				add("Angular");
			}
		};

		Usuario usuario = new Usuario(1, "Nome Teste", "email@test.com", dataNascimento, "Rua do teste, 250",
				habilidades);

		Usuario usuario2 = new Usuario(2, "Nome2 Teste", "email@test.com.br", dataNascimento, "Rua do teste2, 251",
				habilidades);

		List<Usuario> usuarios = new ArrayList<Usuario>() {
			private static final long serialVersionUID = 1L;

			{
				add(usuario);
				add(usuario2);
			}
		};

		when(repository.findByNomeContainingIgnoreCase(nome)).thenReturn(usuarios);

		List<Usuario> usuariosReturn = service.findByNome(nome);
		Assertions.assertEquals(usuariosReturn.contains(usuario), true);
	}

	@Test
	void findByIdTest() throws JsonProcessingException, Exception {
		Integer id = 1;
		LocalDate dataNascimento = LocalDate.ofYearDay(2005, 1);
		List<String> habilidades = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;

			{
				add("Java");
				add("C");
				add("Angular");
			}
		};

		Optional<Usuario> usuario = Optional
				.of(new Usuario(id, "Nome Teste", "email@test.com", dataNascimento, "Rua do teste, 250", habilidades));

		when(repository.findById(id)).thenReturn(usuario);

		Usuario usuarioReturn = service.findById(id);
		Assertions.assertEquals(usuarioReturn.getNome(), "Nome Teste");
	}

}
