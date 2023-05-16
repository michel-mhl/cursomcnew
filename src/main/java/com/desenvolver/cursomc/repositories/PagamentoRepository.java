package com.desenvolver.cursomc.repositories;

import com.desenvolver.cursomc.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
