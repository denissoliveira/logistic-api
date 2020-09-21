package com.logistic.services;

import org.springframework.data.domain.Page;

import com.logistic.domain.Categoria;
import com.logistic.dto.CategoriaDTO;
import com.logistic.services.generic.IDeleteService;
import com.logistic.services.generic.IFindService;
import com.logistic.services.generic.IInsertService;
import com.logistic.services.generic.IUpdateService;

public interface ICategoriaService extends IFindService<Categoria>, IInsertService<Categoria>, IUpdateService<Categoria>, IDeleteService<Categoria> {
	
	Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
	Categoria fromDTO(CategoriaDTO objDTO);

}
