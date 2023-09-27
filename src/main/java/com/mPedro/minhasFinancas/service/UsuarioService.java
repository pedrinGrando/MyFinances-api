package com.mPedro.minhasFinancas.service;

import com.mPedro.minhasFinancas.model.entity.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);

	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);
	
}
