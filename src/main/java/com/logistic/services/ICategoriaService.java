package com.logistic.services;

import org.springframework.data.domain.Page;

import com.logistic.domain.Categoria;

public interface ICategoriaService extends IGenericService<Categoria> {
	
	Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);

}
