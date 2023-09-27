package com.mPedro.minhasFinancas.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mPedro.minhasFinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	// You can add custom query methods here if needed.

	boolean existsByEmail(String email);

	Optional<Usuario> findByEmail(String email);
}
