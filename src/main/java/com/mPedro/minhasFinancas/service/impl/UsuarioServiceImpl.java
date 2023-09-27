package com.mPedro.minhasFinancas.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mPedro.minhasFinancas.exceptions.ErroAutenticacao;
import com.mPedro.minhasFinancas.exceptions.RegraNegocioException;
import com.mPedro.minhasFinancas.model.entity.Usuario;
import com.mPedro.minhasFinancas.model.repository.UsuarioRepository;
import com.mPedro.minhasFinancas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {

		Optional<Usuario> usuario = repository.findByEmail(email);

		if (!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuário não autenticado para o email informado!");
		}

		if (usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida!");
		}

		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		validarEmail(usuario.getEmail());

		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);

		if (existe) {
			throw new RegraNegocioException("Já existe um usuário com este email!");
		}
	}

}
