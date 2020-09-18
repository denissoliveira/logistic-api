package com.logistic.services.generic;


import org.springframework.transaction.annotation.Transactional;
	
public interface IGenericInsertService<T> extends IGenericService<T> {
	
	@Transactional
	T insert(T obj);

}
