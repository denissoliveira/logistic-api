package com.logistic.services.generic;

import org.springframework.transaction.annotation.Transactional;
	
public interface IGenericDeleteService<T> extends IGenericUpdateService<T> {
	
	@Transactional
	void delete(Integer id);

}
