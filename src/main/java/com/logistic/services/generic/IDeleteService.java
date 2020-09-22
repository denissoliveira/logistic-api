package com.logistic.services.generic;

import org.springframework.transaction.annotation.Transactional;

public interface IDeleteService<T> extends IService<T> {
	
	@Transactional
	void delete(Integer id);
	
}
