package com.logistic.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
	
@Transactional(readOnly = true)
public interface IGenericService<T> {
	
	List<T> findAll();
	
	T find(Integer id);
	
	@Transactional
	T insert(T obj);
	
	@Transactional
	T update(T obj);
	
	@Transactional
	void delete(Integer id);

}
