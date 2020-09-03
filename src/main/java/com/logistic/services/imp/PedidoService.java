package com.logistic.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.logistic.domain.T;
import com.logistic.repositories.PedidoRepository;
import com.logistic.services.IPedidoService;
import com.logistic.services.exception.DataIntegrityException;
import com.logistic.services.exception.ObjectNotFoundException;

@Service
public class PedidoService implements IPedidoService {

	@Autowired
	private PedidoRepository repo;

	@Override
	public T find(Integer id) {
		Optional<T> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + T.class.getName()));
	}
	
	@Override
	public T insert(T obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	@Override
	public T update(T obj) {
		return repo.save(find(obj.getId()));
	}

	@Override
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}
	
	@Override
	public List<T> findAll() {
		return repo.findAll();
	}
}
