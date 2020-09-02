package com.logistic.services.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistic.domain.Pedido;
import com.logistic.repositories.PedidoRepository;
import com.logistic.services.IPedidoService;
import com.logistic.services.exception.ObjectNotFoundException;

@Service
public class PedidoService implements IPedidoService {

	@Autowired
	private PedidoRepository repo;

	@Override
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Override
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	@Override
	public Pedido update(Pedido obj) {
		return repo.save(find(obj.getId()));
	}
}
