package com.desenvolver.cursomc;

import com.desenvolver.cursomc.domain.*;
import com.desenvolver.cursomc.enums.EstadoPagamento;
import com.desenvolver.cursomc.enums.TipoCliente;
import com.desenvolver.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
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

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "INFORMATICA");
        Categoria cat2 = new Categoria(null, "ESCRITORIO");
        Categoria cat3 = new Categoria(null, "CAMA MESA E BANHO");
        Categoria cat4 = new Categoria(null, "ELETRONICO");
        Categoria cat5 = new Categoria(null, "JARDINAGEM");
        Categoria cat6 = new Categoria(null, "PERFUMARIA");

        Produto p1 = new Produto(null, "COMPUTADOR", 2000.00);
        Produto p2 = new Produto(null, "IMPRESSORA", 800.00);
        Produto p3 = new Produto(null, "MOUSE", 80.00);


        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));


        categoriaRepository.saveAll(Arrays.asList(cat1, cat2,cat3,cat4,cat5,cat6));
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

        Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","6666666", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("123456","654321"));

        Endereco e1 = new Endereco(null,"Rua das Flores","300","apto 203","Jardim","38220-834",c1,cli1);
        Endereco e2 = new Endereco(null,"Avenida Matos","100","sala 800","Centro","31220-100",c2,cli1);


        cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
        clienterRepoitory.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1,e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO,ped1,6);
        ped1.setPagamento(pgto1);
        Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE,ped2,sdf.parse("20/10/2017 00:00"),null);
        ped2.setPagamento(pgto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
        pagamentoRepository.saveAll(Arrays.asList(pgto1,pgto2));

        ItemPedido ip1 = new ItemPedido(ped1,p1,00.00,1,2000.00);
        ItemPedido ip2 = new ItemPedido(ped1,p3,00.00,2,80.00);
        ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1,800.00);

        ped1.getItemPedidos().addAll(Arrays.asList(ip1,ip2));
        ped2.getItemPedidos().addAll(Arrays.asList(ip3));

        p1.getItemPedidos().addAll(Arrays.asList(ip1));
        p2.getItemPedidos().addAll(Arrays.asList(ip3));
        p3.getItemPedidos().addAll(Arrays.asList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));











    }
}
