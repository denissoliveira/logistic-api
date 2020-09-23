package com.logistic.services.imp;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistic.domain.ItemPedido;
import com.logistic.domain.PagamentoComBoleto;
import com.logistic.domain.Pedido;
import com.logistic.domain.enums.EstadoPagamento;
import com.logistic.repositories.ClienteRepository;
import com.logistic.repositories.ItemPedidoRepository;
import com.logistic.repositories.PagamentoRepository;
import com.logistic.repositories.PedidoRepository;
import com.logistic.repositories.ProdutoRepository;
import com.logistic.services.IEmailService;
import com.logistic.services.IPedidoService;
import com.logistic.services.exception.ObjectNotFoundException;

@Service
public class PedidoService implements IPedidoService {
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private IEmailService emailService;
	
	@Override
	public Pedido find(Integer id) {
		logger.debug("Buscando pedido por ID.");
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Override
	public List<Pedido> findAll() {
		logger.debug("Buscando todos os pedido.");
		return repo.findAll();
	}

	@Override
	public Pedido insert(Pedido obj) {
		logger.debug("Salvando um pedido pedido.");
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteRepository.findById(obj.getCliente().getId()).get());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoRepository.findById(ip.getProduto().getId()).get());
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}

}
