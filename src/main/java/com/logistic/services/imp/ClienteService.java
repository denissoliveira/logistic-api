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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistic.domain.Cidade;
import com.logistic.domain.Cliente;
import com.logistic.domain.Endereco;
import com.logistic.domain.enums.TipoCliente;
import com.logistic.dto.ClienteDTO;
import com.logistic.dto.ClienteNewDTO;
import com.logistic.repositories.ClienteRepository;
import com.logistic.repositories.EnderecoRepository;
import com.logistic.services.IClienteService;
import com.logistic.services.exception.DataIntegrityException;
import com.logistic.services.exception.ObjectNotFoundException;

@Service
public class ClienteService implements IClienteService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private BCryptPasswordEncoder pe;
	@Autowired private ClienteRepository repo;
	@Autowired private EnderecoRepository enderecoRepository;
	
	@Override
	public Cliente find(Integer id) {
		logger.debug("Buscando cliente por ID");
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	@Override
	public Cliente insert(Cliente obj) {
		logger.debug("Salvando cliente");
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	@Transactional
	@Override
	public Cliente update(Cliente obj) {
		logger.debug("Atualizando cliente");
		Cliente newObj = find(obj.getId());
		updateDate(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateDate(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		logger.debug("Excluindo cliente");
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas");
		}
	}
	
	@Override
	public List<Cliente> findAll() {
		logger.debug("Buscando todos os clientes");
		return repo.findAll();
	}

	@Override
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		logger.debug("Obter cliente paginado");
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	@Override
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente.Builder(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null).build();
	}
	
	@Override
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente.Builder(
					objDTO.getId(), 
					objDTO.getNome(), 
					objDTO.getEmail(), 
					objDTO.getCpfOuCnpj(), 
					pe.encode(objDTO.getSenha()), 
					TipoCliente.toEnum(objDTO.getTipo())
				).build();
		Cidade cid = new Cidade.Builder(objDTO.getCidadeId()).build();
		Endereco end = new Endereco.Builder(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), 
				cid).setCliente(cli).build();
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		cli.getTelefones().add(Optional.ofNullable(objDTO.getTelefone2()).orElse(null));
		cli.getTelefones().add(Optional.ofNullable(objDTO.getTelefone3()).orElse(null));
		return cli;
	}
}
