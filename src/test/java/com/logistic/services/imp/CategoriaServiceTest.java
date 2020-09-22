package com.logistic.services.imp;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
		assertNotNull(categoriaService.find(ID));
	}
	
	@Test
	public void buscarCategoriaPeloIdQueNaoExiste () {
		when(categoriaRepository.findById(ID)).thenReturn(Optional.ofNullable(null));
		try {
			assertNotNull(categoriaService.find(ID));
		} catch (Exception e) {
			//assertThat(e.getMessage(), CoreMatchers.is("Objeto não encontrado! Id: 1, Tipo: com.logistic.domain.Categoria"));
			//assertThat
		}
	}
	
	@Test
	public void buscarListaCategorias () {
		assertNotNull(categoriaService.findAll());
	}

}
