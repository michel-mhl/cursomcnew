package com.desenvolver.cursomc;

import com.desenvolver.cursomc.domain.*;
import com.desenvolver.cursomc.enums.TipoCliente;
import com.desenvolver.cursomc.repositories.*;
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
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienterRepoitory;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "INFORMATICA");
        Categoria cat2 = new Categoria(null, "ESCRITORIO");

        Produto p1 = new Produto(null, "COMPUTADOR", 2000.00);
        Produto p2 = new Produto(null, "IMPRESSORA", 800.00);
        Produto p3 = new Produto(null, "MOUSE", 80.00);


        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));


        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlandia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1,est2));
        cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

        Cliente cli1 = new Cliente(null,"Maria Silva","1234567890", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("123456","654321"));

        Endereco e1 = new Endereco(null,"Rua das Flores","300","apto 203","Jardim","38220-834",c1,cli1);
        Endereco e2 = new Endereco(null,"Avenida Matos","100","sala 800","Centro","31220-100",c2,cli1);


       //cli1.getEnderecos().addAll(Arrays.asList(e1,e2)); comando de inserção invalidando o processo

        clienterRepoitory.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1,e2));










    }
}
