package com.logistic.services;

import org.springframework.data.domain.Page;

import com.logistic.domain.Cliente;
import com.logistic.dto.ClienteDTO;

public interface IClienteService extends IGenericService<Cliente> {
	
	Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
	Cliente fromDTO(ClienteDTO objDTO);

}
