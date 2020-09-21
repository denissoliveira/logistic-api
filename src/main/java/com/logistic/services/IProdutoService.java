package com.logistic.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.logistic.domain.Produto;
import com.logistic.services.generic.IGenericService;

public interface IProdutoService extends IGenericService<Produto> {
	
	Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction);

}
