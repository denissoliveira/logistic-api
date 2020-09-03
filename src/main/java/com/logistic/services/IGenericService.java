package com.logistic.services;

import java.util.List;

public interface IGenericService<T> {
	
	List<T> findAll();
	
	T find(Integer id);
	
	T insert(T obj);
	
	T update(T obj);
	
	void delete(Integer id);

}
