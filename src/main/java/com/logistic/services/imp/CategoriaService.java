package com.logistic.services.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistic.domain.Categoria;
import com.logistic.repositories.CategoriaRepository;
import com.logistic.services.ICategoriaService;
import com.logistic.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService implements ICategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	@Override
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	@Override
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	@Override
	public Categoria update(Categoria obj) {
		return repo.save(find(obj.getId()));
	}
}
