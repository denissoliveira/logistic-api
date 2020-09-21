package com.logistic.resources.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.logistic.domain.Produto;
import com.logistic.dto.ProdutoDTO;
import com.logistic.resources.IProdutoResource;
import com.logistic.resources.utils.URL;
import com.logistic.services.imp.ProdutoService;

@RestController
public class ProdutoResource implements IProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;

	@Override
	public ResponseEntity<Produto> find(Integer id) {
		Produto pedido = produtoService.find(id);
		return ResponseEntity.ok().body(pedido);
	}

	@Override
	public ResponseEntity<Page<ProdutoDTO>> findPage(String nome, String categorias, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		String nomeDecoded = URL.decodeParam(nome); // Converte o encode !20%"
		List<Integer> ids = URL.decodeIntList(categorias); // pega o string e converte para o ids integer de categorias
		Page<Produto> list = produtoService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO.Builder(obj).build());  
		return ResponseEntity.ok().body(listDto);
	}

}
