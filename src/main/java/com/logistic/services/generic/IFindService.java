package com.logistic.services.generic;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface IFindService<T> extends IService<T> {
	
	List<T> findAll();
	
	T find(Integer id);
	
}
