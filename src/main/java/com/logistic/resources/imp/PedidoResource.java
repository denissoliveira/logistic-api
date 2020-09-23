package com.logistic.resources.imp;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.logistic.domain.Pedido;
import com.logistic.resources.IPedidoResource;
import com.logistic.services.imp.PedidoService;

@RestController
public class PedidoResource implements IPedidoResource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PedidoService pedidoService;

	@Override
	public ResponseEntity<Pedido> find(Integer id) {
		logger.debug("resources: Obtendo Pedido por ID");
		Pedido pedido = pedidoService.find(id);
		return ResponseEntity.ok().body(pedido);
	}

	@Override
	public ResponseEntity<Void> insert(Pedido obj) {
		logger.debug("resources: salvando Pedido");
		obj = pedidoService.insert(obj);
		//Monta a url com o id do obj criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); //Já retorna um 201 com a url
	}

}
