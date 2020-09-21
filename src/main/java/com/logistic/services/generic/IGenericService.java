package com.logistic.services.generic;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface IGenericService<T> {
	
	List<T> findAll();
	
	T find(Integer id);
	
	T insert(T obj);
	
	T update(T obj);
	
	void delete(Integer id);
}
