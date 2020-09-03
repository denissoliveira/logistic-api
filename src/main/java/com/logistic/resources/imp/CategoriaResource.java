package com.logistic.resources.imp;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.logistic.domain.Categoria;
import com.logistic.dto.CategoriaDTO;
import com.logistic.resources.IcategoriaResource;
import com.logistic.services.imp.CategoriaService;

@RestController
public class CategoriaResource implements IcategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Override
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> categorias = categoriaService.findAll();
		List<CategoriaDTO> categoriaDTOList = categorias.stream().map(obj -> new CategoriaDTO.Builder(obj).builder()).collect(Collectors.toList());
		return ResponseEntity.ok().body(categoriaDTOList);
	}

	@Override
	public ResponseEntity<Categoria> find(Integer id) {
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

	@Override
	public ResponseEntity<Void> update(Categoria obj, Integer id) {
		obj.setId(id);
		obj = categoriaService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> delete(Integer id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
