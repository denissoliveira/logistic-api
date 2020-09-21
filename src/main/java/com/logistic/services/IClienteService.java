package com.logistic.services;

import org.springframework.data.domain.Page;

import com.logistic.domain.Cliente;
import com.logistic.dto.ClienteDTO;
import com.logistic.dto.ClienteNewDTO;
import com.logistic.services.generic.IGenericService;

public interface IClienteService extends IGenericService<Cliente> {
	
	Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
	
	Cliente fromDTO(ClienteDTO objDTO);
	
	Cliente fromDTO(ClienteNewDTO objDTO);

}
