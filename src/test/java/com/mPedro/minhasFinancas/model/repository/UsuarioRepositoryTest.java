package com.mPedro.minhasFinancas.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mPedro.minhasFinancas.model.entity.Usuario;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;

	@Autowired
	TestEntityManager entityManager;

	@Test
	public void deveVerificarAExistenciaDeUmEmail() {

		// Cenário
		// Usuario user1 =
		// Usuario.builder().nome("Pedro").email("pedrogrando5@gmail.com").build();
		Usuario user1 = new Usuario(0, "Pedro", "pedrogrando5@gmail.com", null);
		entityManager.persist(user1);

		// Ação
		boolean result = repository.existsByEmail("pedrogrando5@gmail.com");
		// Verificação
		Assertions.assertThat(result).isTrue();

	}

	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {

		// CENÁRIO

		// boolean
		boolean result = repository.existsByEmail("usuario@gmail.com");

		// VERIFICACAO
		Assertions.assertThat(result).isFalse();
	}

	@Test
	public void devePersistirUmUsuarioNabaseDeDados() {
		// Cenário
		Usuario usuario = criarUsuario();

		// ACAO
		repository.save(usuario);

		Assertions.assertThat(usuario.getId()).isNotNull();
	}

	@Test
	public void deveBuscarUmUsuarioPorEmail() {
		// Cenário
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);

		// VERIFICACAO
		Optional<Usuario> result = repository.findByEmail("usuario@email.com");

		Assertions.assertThat(result.isPresent()).isTrue();

	}

	@Test
	public void deveRetornarVazioAoBuscarUsuarioQuandoNaoExisteNaBase() {

		// VERIFICACAO
		Optional<Usuario> result = repository.findByEmail("usuario@email.com");

		Assertions.assertThat(result.isPresent()).isFalse();

	}

	public static Usuario criarUsuario() {
		Usuario usuario = new Usuario(0, "usuario", "usuario@email.com", "senha");

		return usuario;
	}

}
