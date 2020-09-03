package com.logistic.services.imp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.logistic.domain.Categoria;
import com.logistic.repositories.CategoriaRepository;

// Usar Spring ?? assim ele roda toda a aplicação
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CategoriaServiceTest {
	
	@InjectMocks
	private CategoriaService categoriaService;
	
	@Mock
	private CategoriaRepository categoriaRepository;
	
	@Mock
	private Categoria categoria;
	
	@Mock
	private List<Categoria> categoriaList = new ArrayList<>();
	
	private static final Integer ID = 1;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(categoriaRepository.findById(ID)).thenReturn(Optional.of(categoria));
		when(categoriaRepository.save(categoria)).thenReturn(categoria);
		when(categoriaRepository.findAll()).thenReturn(categoriaList);
	}
	
	@Test
	public void buscarCategoriaPeloId () {
		assertThat(categoriaService.find(ID)).isNotNull();
	}
	
	@Test
	public void buscarListaCategorias () {
		assertThat(categoriaService.findAll()).isNotNull();
	}

//	@Test
//	public void atuaçizarCategoria () {
//		assertThat(categoriaService.update(categoria)).isNotNull();
//	}
	
}
