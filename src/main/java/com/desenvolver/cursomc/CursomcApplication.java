package com.desenvolver.cursomc;

import com.desenvolver.cursomc.domain.Categoria;
import com.desenvolver.cursomc.domain.Produto;
import com.desenvolver.cursomc.repositories.CategoriaRepository;
import com.desenvolver.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
@Autowired
private CategoriaRepository categoriaRepository;
@Autowired
private ProdutoRepository produtoRepository;
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"INFORMATICA");
		Categoria cat2 = new Categoria(null,"ESCRITORIO");

		Produto p1 = new Produto(null,"COMPUTADOR",2000.00);
		Produto p2 = new Produto(null,"IMPRESSORA",800.00);
		Produto p3 = new Produto(null,"MOUSE",80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));



		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

	}
}
