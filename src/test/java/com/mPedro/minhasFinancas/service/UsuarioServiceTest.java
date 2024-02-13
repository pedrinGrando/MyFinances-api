package com.mPedro.minhasFinancas.service;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import com.mPedro.minhasFinancas.exceptions.ErroAutenticacao;
import com.mPedro.minhasFinancas.exceptions.RegraNegocioException;
import com.mPedro.minhasFinancas.model.entity.Usuario;
import com.mPedro.minhasFinancas.model.repository.UsuarioRepository;
import com.mPedro.minhasFinancas.service.impl.UsuarioServiceImpl;

@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@SpyBean
	UsuarioServiceImpl service;

	@MockBean
	UsuarioRepository repository;

	@Test
	public void deveSalvarUmUsuario() {
		// CENARIO
		Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
		Usuario usuario = new Usuario(0, "nome", "email@email.com", "senha");

		Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
		// ACAO
		Usuario usuarioSalvo = service.salvarUsuario(new Usuario());

		// VERIFICACAO
		Assertions.assertThat(usuarioSalvo).isNotNull();
		Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(1l);
		Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo("nome");
		Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo("email@email.com");
		Assertions.assertThat(usuarioSalvo.getSenha()).isEqualTo("senha");

	}

	public void noaDeveSalvarUmUsuarioEmailCadastrado() {
		// CENARIO
		Usuario usuario = new Usuario();
		usuario.setEmail("email@email.com");
		Mockito.doThrow(RegraNegocioException.class).when(service).validarEmail("email@email.com");

		// ACAO
		service.salvarUsuario(usuario);

		// verificacao
		Mockito.verify(repository, Mockito.never()).save(usuario);

	}

	@Test
	public void deveAutenticarUmUserSucesso() {
		// CENARIO
		String email = "email@email.com";
		String senha = "senha";

		Usuario usuario = new Usuario(0, email, senha, senha);

		Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));

		// acao
		Usuario result = service.autenticar(email, senha);

		// verificacao

		Assertions.assertThat(result).isNotNull();
	}

	@Test
	public void deveLancarErroQuandoNaoEncontraUusarioCadastradoComEmailInfo() {

		// cenario
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

		// ACAO
		Throwable exception = Assertions.catchThrowable(() -> service.autenticar("email@email.com", "123"));

		// VERIFICACAO
		Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class)
				.hasMessage("Usuário não encontrado para o email informado!");
	}

	@Test
	public void deveLancarErroQuandoSenhaNaoBater() {

		String senha = "senha";
		Usuario usuario = new Usuario(0, "email@email.com", senha, senha);
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));

		// ACAO
		Throwable exception = Assertions.catchThrowable(() -> service.autenticar("email@email.com", "123"));
		Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("Senha inválida!");

	}

	@Test
	public void deveValidarEmail() {
		// cenario
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);

		// acao
		service.validarEmail("email@email.com");
	}

	@Test
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		// cenario
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);

		// acao
		org.junit.jupiter.api.Assertions.assertThrows(RegraNegocioException.class,
				() -> service.validarEmail("email@email.com"));
	}
}
