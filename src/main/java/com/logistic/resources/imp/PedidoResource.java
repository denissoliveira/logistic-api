package com.logistic.resources.imp;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.logistic.domain.Pedido;
import com.logistic.resources.IPedidoResource;
import com.logistic.services.imp.PedidoService;

@RestController
public class PedidoResource implements IPedidoResource {
	
	@Autowired
	private PedidoService pedidoService;

	@Override
	public ResponseEntity<Pedido> find(Integer id) {
		Pedido pedido = pedidoService.find(id);
		return ResponseEntity.ok().body(pedido);
	}

	@Override
	public ResponseEntity<Void> insert(Pedido obj) {
		obj = pedidoService.insert(obj);
		//Monta a url com o id do obj criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); //Já retorna um 201 com a url
	}

}
