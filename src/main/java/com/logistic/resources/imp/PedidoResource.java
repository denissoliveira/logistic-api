package com.logistic.resources.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.logistic.domain.T;
import com.logistic.resources.IPedidoResource;
import com.logistic.services.imp.PedidoService;

@RestController
public class PedidoResource implements IPedidoResource {
	
	@Autowired
	private PedidoService pedidoService;

	@Override
	public ResponseEntity<T> find(Integer id) {
		T pedido = pedidoService.find(id);
		return ResponseEntity.ok().body(pedido);
	}

}
