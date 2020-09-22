package com.logistic.repository;


import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.logistic.repositories.CategoriaRepository;


// Com o Junit 5 não precisa das anotações abaixo
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoriaRepositoryTest {
	
	private static final Integer ID = 1;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@BeforeEach
	public void setUp() {}
	
	@Test
	public void buscaUmaCategoria() {
		assertNotNull(categoriaRepository.findById(ID));
	}

}
