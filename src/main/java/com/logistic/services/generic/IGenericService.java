package com.logistic.services.generic;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface IGenericService<T> {
	
	@Transactional(readOnly = true)
	List<T> findAll();
	
	@Transactional(readOnly = true)
	T find(Integer id);
	
}
