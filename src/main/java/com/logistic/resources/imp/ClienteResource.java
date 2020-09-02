package com.logistic.resources.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.logistic.domain.Cliente;
import com.logistic.resources.IclienteResource;
import com.logistic.services.imp.ClienteService;

@RestController
public class ClienteResource implements IclienteResource {
	
	@Autowired
	private ClienteService clienteService;

	@Override
	public ResponseEntity<Cliente> find(Integer id) {
		Cliente cliente = clienteService.find(id);
		return ResponseEntity.ok().body(cliente);
	}

}
