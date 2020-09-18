package com.logistic.repositories;

import org.springframework.stereotype.Repository;

import com.logistic.domain.Produto;

@Repository
public interface ProdutoRepository extends IBaseRepository<Produto, Integer>{

}
