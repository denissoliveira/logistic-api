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

import com.logistic.domain.Cliente;
import com.logistic.dto.ClienteDTO;
import com.logistic.dto.ClienteNewDTO;
import com.logistic.resources.IclienteResource;
import com.logistic.services.imp.ClienteService;

@RestController
public class ClienteResource implements IclienteResource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ClienteService clienteService;
	
	@Override
	public ResponseEntity<List<ClienteDTO>> findAll() {
		logger.debug("resources: Obtendo todos os clientes");
		List<Cliente> Clientes = clienteService.findAll();
		List<ClienteDTO> categoriaDTOList = Clientes.stream().map(obj -> new ClienteDTO.Builder(obj).build()).collect(Collectors.toList());
		return ResponseEntity.ok().body(categoriaDTOList);
	}

	@Override
	public ResponseEntity<Cliente> find(Integer id) {
		logger.debug("resources: Obtendo cliente por ID");
		Cliente cliente = clienteService.find(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@Override
	public ResponseEntity<Void> insert(ClienteNewDTO objDTO) {
		logger.debug("resources: Salvando cliente");
		Cliente obj = clienteService.fromDTO(objDTO);
		obj = clienteService.insert(obj);
		//Monta a url com o id do obj criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); //Já retorna um 201 com a url
	}

	@Override
	public ResponseEntity<Void> update(ClienteDTO objDTO, Integer id) {
		logger.debug("resources: Atualizando cliente");
		Cliente obj = clienteService.fromDTO(objDTO);
		obj.setId(id);
		obj = clienteService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> delete(Integer id) {
		logger.debug("resources: Deletando cliente");
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Page<ClienteDTO>> findPage(Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		logger.debug("resources: Obtendo clientes paginado");
		Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO.Builder(obj).build());
		return ResponseEntity.ok().body(listDTO);
	}

}
