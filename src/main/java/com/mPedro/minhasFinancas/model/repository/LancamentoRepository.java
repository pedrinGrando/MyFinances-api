package com.mPedro.minhasFinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mPedro.minhasFinancas.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
