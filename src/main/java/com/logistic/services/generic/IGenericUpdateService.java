package com.logistic.services.generic;

import org.springframework.transaction.annotation.Transactional;
	
public interface IGenericUpdateService<T> extends IGenericInsertService<T> {
	
	@Transactional
	T update(T obj);

}
