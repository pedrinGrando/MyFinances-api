package com.mPedro.minhasFinancas.model.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.mPedro.minhasFinancas.model.entity.Lancamento;
import com.mPedro.minhasFinancas.model.entity.Usuario;
import com.mPedro.minhasFinancas.model.enums.StatusLancamento;
import com.mPedro.minhasFinancas.model.enums.TipoLancamento;

@RunWith( SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase( replace = Replace.NONE)
@ActiveProfiles("test")
public class LancamentoRepositoryTest {

	
	@Autowired
	LancamentoRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	
	@Test
	public void deveLancarUmLancamento() {
		Lancamento lancamento = new Lancamento(1,"Lançamento qualquer",  1, 2019, BigDecimal.valueOf(10), TipoLancamento.RECEITA, StatusLancamento.PENDENTE);
		
		lancamento = repository.save(lancamento);
		
		Assertions.assertThat(lancamento.getId()).isNotNull();
		
	}
	
	
}
