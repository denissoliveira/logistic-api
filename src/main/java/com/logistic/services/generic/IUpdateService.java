package com.logistic.services.generic;

import org.springframework.transaction.annotation.Transactional;

public interface IUpdateService<T> extends IService<T> {
	
	@Transactional
	T update(T obj);
	
}
