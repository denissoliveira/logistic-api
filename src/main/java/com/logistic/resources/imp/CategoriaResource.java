package com.logistic.resources.imp;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	@Override
	public ResponseEntity<Void> insert(Categoria obj) {
		obj = categoriaService.insert(obj);
		//Monta a url com o id do obj criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); //Já retorna um 201 com a url
	}

}
