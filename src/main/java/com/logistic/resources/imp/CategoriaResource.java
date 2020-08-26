package com.logistic.resources.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.logistic.domain.Categoria;
import com.logistic.resources.IcategoriaResource;

@RestController
public class CategoriaResource implements IcategoriaResource {

	@Override
	public List<Categoria> listar() {
		
		Categoria cat1 = new Categoria.Builder(1, "Informática").builder();
		Categoria cat2 = new Categoria.Builder(2, "Escritório").builder();
		
		List<Categoria> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		return lista;
	}

}
