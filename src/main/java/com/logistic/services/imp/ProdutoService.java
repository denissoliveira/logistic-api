package com.logistic.services.imp;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.logistic.domain.Produto;
import com.logistic.repositories.CategoriaRepository;
import com.logistic.repositories.ProdutoRepository;
import com.logistic.services.IProdutoService;
import com.logistic.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService implements IProdutoService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		logger.debug("Buscando por filtro, produtos");
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		//return repo.search(nome, categoriaRepository.findAllById(ids), pageRequest);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categoriaRepository.findAllById(ids), pageRequest);
	}

	@Override
	public Produto find(Integer id) {
		logger.debug("Buscando produto por ID");
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	@Override
	public List<Produto> findAll() {
		logger.debug("Buscando todos os produtos");
		return repo.findAll();
	}

}
