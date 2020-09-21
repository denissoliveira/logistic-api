package com.logistic.services.imp;

import java.util.List;
import java.util.Optional;

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

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		//return repo.search(nome, categoriaRepository.findAllById(ids), pageRequest);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categoriaRepository.findAllById(ids), pageRequest);
	}

	@Override
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	@Override
	public List<Produto> findAll() {
		return repo.findAll();
	}

}
