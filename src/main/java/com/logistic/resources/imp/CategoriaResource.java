package com.logistic.resources.imp;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.logistic.domain.Categoria;
import com.logistic.dto.CategoriaDTO;
import com.logistic.resources.IcategoriaResource;
import com.logistic.services.imp.CategoriaService;

@RestController
public class CategoriaResource implements IcategoriaResource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Override
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		logger.debug("resources: Obtendo todas as categorias");
		List<Categoria> categorias = categoriaService.findAll();
		List<CategoriaDTO> categoriaDTOList = categorias.stream().map(obj -> new CategoriaDTO.Builder(obj).build()).collect(Collectors.toList());
		return ResponseEntity.ok().body(categoriaDTOList);
	}

	@Override
	public ResponseEntity<Categoria> find(Integer id) {
		logger.debug("resources: Obtendo categoria por ID");
		Categoria categoria = categoriaService.find(id);
		return ResponseEntity.ok().body(categoria);
	}

	@Override
	public ResponseEntity<Void> insert(CategoriaDTO objDTO) {
		logger.debug("resources: Salvando categoria");
		Categoria obj = categoriaService.fromDTO(objDTO);
		obj = categoriaService.insert(obj);
		//Monta a url com o id do obj criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); //Já retorna um 201 com a url
	}

	@Override
	public ResponseEntity<Void> update(CategoriaDTO objDTO, Integer id) {
		logger.debug("resources: Atualizando categoria");
		Categoria obj = categoriaService.fromDTO(objDTO);
		obj.setId(id);
		obj = categoriaService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> delete(Integer id) {
		logger.debug("resources: Deletando categoria por ID");
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Page<CategoriaDTO>> findPage(Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		logger.debug("resources: Obtendo categorias paginado");
		Page<Categoria> list = categoriaService.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO.Builder(obj).build());
		return ResponseEntity.ok().body(listDTO);
	}

}
