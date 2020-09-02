package com.logistic.services;

public interface IGenericService<T> {
	
	T find(Integer id);
	
	T insert(T obj);
	
	T update(T obj);
	
	void delete(Integer id);

}
