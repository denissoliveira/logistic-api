package com.logistic.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.logistic.repositories.CategoriaRepository;

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
