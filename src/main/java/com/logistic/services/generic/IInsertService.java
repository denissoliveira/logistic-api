package com.logistic.services.generic;

import org.springframework.transaction.annotation.Transactional;

public interface IInsertService<T> extends IService<T> {
	
	@Transactional
	T insert(T obj);
	
}
