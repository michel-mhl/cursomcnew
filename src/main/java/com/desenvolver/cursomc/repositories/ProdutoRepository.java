package com.desenvolver.cursomc.repositories;

import com.desenvolver.cursomc.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {
}
