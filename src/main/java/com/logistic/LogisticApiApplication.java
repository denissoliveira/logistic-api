package com.logistic;

import static java.util.Arrays.asList;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.logistic.domain.Categoria;
import com.logistic.domain.Cidade;
import com.logistic.domain.Cliente;
import com.logistic.domain.Endereco;
import com.logistic.domain.Estado;
import com.logistic.domain.ItemPedido;
import com.logistic.domain.Pagamento;
import com.logistic.domain.PagamentoComBoleto;
import com.logistic.domain.PagamentoComCartao;
import com.logistic.domain.Pedido;
import com.logistic.domain.Produto;
import com.logistic.domain.enums.EstadoPagamento;
import com.logistic.domain.enums.TipoCliente;
import com.logistic.repositories.CategoriaRepository;
import com.logistic.repositories.CidadeRepository;
import com.logistic.repositories.ClienteRepository;
import com.logistic.repositories.EnderecoRepository;
import com.logistic.repositories.EstadoRepository;
import com.logistic.repositories.ItemPedidoRepository;
import com.logistic.repositories.PagamentoRepository;
import com.logistic.repositories.PedidoRepository;
import com.logistic.repositories.ProdutoRepository;

@SpringBootApplication
public class LogisticApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LogisticApiApplication.class, args);
	}
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Produto p1 = new Produto.Builder(null, "Computador", 2000.00).build();
		Produto p2 = new Produto.Builder(null, "Impressora", 800.00).build();
		Produto p3 = new Produto.Builder(null, "Mouse", 80.00).build();
		Produto p4 = new Produto.Builder(null, "Mesa de escritório", 300.00).build();
		Produto p5 = new Produto.Builder(null, "Toalha", 50.00).build();
		Produto p6 = new Produto.Builder(null, "Colcha", 200.00).build();
		Produto p7 = new Produto.Builder(null, "TV true color", 1200.00).build();
		Produto p8 = new Produto.Builder(null, "Roçadeira", 800.00).build();
		Produto p9 = new Produto.Builder(null, "Abajour", 100.00).build();
		Produto p10 = new Produto.Builder(null, "Pendente", 180.00).build();
		Produto p11 = new Produto.Builder(null, "Shampoo", 90.00).build();
		
		Categoria cat1 = new Categoria.Builder(null, "Informática").build();
		Categoria cat2 = new Categoria.Builder(null, "Escritório").build();
		Categoria cat3 = new Categoria.Builder(null, "Cama mesa banho").build();
		Categoria cat4 = new Categoria.Builder(null, "Eletrônicos").build();
		Categoria cat5 = new Categoria.Builder(null, "Jardinagem").build();
		Categoria cat6 = new Categoria.Builder(null, "Decoração").build();
		Categoria cat7 = new Categoria.Builder(null, "Perfumaria").build();
		
		cat1.getProdutos().addAll(asList(p1,p2,p3));
		cat2.getProdutos().addAll(asList(p2,p4));
		cat3.getProdutos().addAll(asList(p5,p6));
		cat4.getProdutos().addAll(asList(p1,p2,p3,p7));
		cat5.getProdutos().addAll(asList(p8));
		cat6.getProdutos().addAll(asList(p9,p10));
		cat7.getProdutos().addAll(asList(p11));
		
		p1.getCategorias().addAll(asList(cat1, cat4));
		p2.getCategorias().addAll(asList(cat1,cat2, cat4));
		p3.getCategorias().addAll(asList(cat1, cat4));
		p4.getCategorias().addAll(asList(cat2));
		p5.getCategorias().addAll(asList(cat3));
		p6.getCategorias().addAll(asList(cat3));
		p7.getCategorias().addAll(asList(cat4));
		p8.getCategorias().addAll(asList(cat5));
		p9.getCategorias().addAll(asList(cat6));
		p10.getCategorias().addAll(asList(cat6));
		p11.getCategorias().addAll(asList(cat7));
		
		categoriaRepository.saveAll(asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		produtoRepository.saveAll(asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		
		Estado est1 = new Estado.Builder(null, "Minas Gerais").builder();
		Estado est2 = new Estado.Builder(null, "São Paulo").builder();
		
		Cidade c1 = new Cidade.Builder(null).nome("Uberlândia").estado(est1).build();
		Cidade c2 = new Cidade.Builder(null).nome("São Paulo").estado(est2).build();
		Cidade c3 = new Cidade.Builder(null).nome("Campinas").estado(est2).build();
		
		est1.getCidades().addAll(asList(c1));
		est2.getCidades().addAll(asList(c2,c3));
		
		estadoRepository.saveAll(asList(est1,est2));
		cidadeRepository.saveAll(asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente.Builder(null, "Denis Oliveira", "email@example.com", "18379451015", TipoCliente.PESSOAFISICA).build();
		
		cli1.getTelefones().addAll(asList("1562-1562","7584-9568"));
		
		Endereco e1 = new Endereco.Builder(null, "Rua Flores", "300", "Apto 303", "Jardim", "265184865", c1).setCliente(cli1).build();
		Endereco e2 = new Endereco.Builder(null, "Avenida Matos", "105", "Sala 800", "Centro", "15263154", c1).setCliente(cli1).build();
		
		cli1.getEnderecos().addAll(asList(e1,e2));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido.Builder(null, sdf.parse("30/09/1998 10:32"), cli1, e1).builder();
		Pedido ped2 = new Pedido.Builder(null, sdf.parse("10/10/1998 19:35"), cli1, e2).builder();

		cli1.getPedidos().addAll(asList(ped1,ped2));
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("11/10/1998 10:10"), sdf.parse("11/11/1998 10:10"));
				
		ped1.setPagamento(pagto1);
		ped2.setPagamento(pagto2);
		
		pedidoRepository.saveAll(asList(ped1,ped2));
		pagamentoRepository.saveAll(asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido.Builder(ped1, p1, 0.00, 1, 2000.00).builder();
		ItemPedido ip2 = new ItemPedido.Builder(ped1, p3, 0.00, 2, 80.00).builder();
		ItemPedido ip3 = new ItemPedido.Builder(ped2, p2, 100.00, 1, 800.00).builder();
		
		ped1.getItens().addAll(asList(ip1,ip2));
		ped2.getItens().addAll(asList(ip3));
		
		p1.getItens().addAll(asList(ip1));
		p2.getItens().addAll(asList(ip3));
		p3.getItens().addAll(asList(ip1));
		
		itemPedidoRepository.saveAll(asList(ip1,ip2,ip3));
	}

}
