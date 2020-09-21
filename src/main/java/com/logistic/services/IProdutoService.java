package com.logistic.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.logistic.domain.Produto;
import com.logistic.services.generic.IFindService;

public interface IProdutoService extends IFindService<Produto> {
	
	Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction);

}
