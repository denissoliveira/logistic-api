package com.logistic.services.imp;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistic.domain.Categoria;
import com.logistic.dto.CategoriaDTO;
import com.logistic.repositories.CategoriaRepository;
import com.logistic.services.ICategoriaService;
import com.logistic.services.exception.DataIntegrityException;
import com.logistic.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService implements ICategoriaService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CategoriaRepository repo;
	
	@Override
	public Categoria find(Integer id) {
		logger.debug("Buscando categoria por ID");
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	@Transactional
	@Override
	public Categoria insert(Categoria obj) {
		logger.debug("Salvando categoria");
		obj.setId(null);
		return repo.save(obj);
	}

	@Transactional
	@Override
	public Categoria update(Categoria obj) {
		logger.debug("Atualizando categoria");
		Categoria newObj = find(obj.getId());
		updateDate(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateDate(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		logger.debug("Deletando categoria por ID");
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}

	@Override
	public List<Categoria> findAll() {
		logger.debug("Buscando todas as categorias");
		return repo.findAll();
	}

	@Override
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		logger.debug("Buscando categoria paginado");
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	@Override
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria.Builder(objDTO.getId(), objDTO.getNome()).build();
	}
	
}
