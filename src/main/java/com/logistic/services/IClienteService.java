package com.logistic.services;

import org.springframework.data.domain.Page;

import com.logistic.domain.Cliente;
import com.logistic.dto.ClienteDTO;
import com.logistic.dto.ClienteNewDTO;
import com.logistic.services.generic.IDeleteService;
import com.logistic.services.generic.IFindService;
import com.logistic.services.generic.IInsertService;
import com.logistic.services.generic.IUpdateService;

public interface IClienteService extends IFindService<Cliente>, IInsertService<Cliente>, IUpdateService<Cliente>, IDeleteService<Cliente> {
	
	Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
	
	Cliente fromDTO(ClienteDTO objDTO);
	
	Cliente fromDTO(ClienteNewDTO objDTO);

}
