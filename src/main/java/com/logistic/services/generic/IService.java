package com.logistic.services.generic;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface IService<T> {
	
}
