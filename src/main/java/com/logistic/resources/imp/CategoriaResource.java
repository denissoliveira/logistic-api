package com.logistic.resources.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.logistic.domain.Categoria;
import com.logistic.resources.IcategoriaResource;
import com.logistic.services.imp.CategoriaService;

@RestController
public class CategoriaResource implements IcategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;

	@Override
	public ResponseEntity<?> find(Integer id) {
		Categoria categoria = categoriaService.find(id);
		return ResponseEntity.ok().body(categoria);
	}

}
