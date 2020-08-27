package com.logistic.services.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistic.domain.Cliente;
import com.logistic.repositories.ClienteRepository;
import com.logistic.services.IClienteService;
import com.logistic.services.exception.ObjectNotFoundException;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Override
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
}
