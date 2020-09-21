package com.logistic.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.logistic.contants.IConstants;
import com.logistic.domain.Cliente;
import com.logistic.dto.ClienteDTO;
import com.logistic.dto.ClienteNewDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping(value = IConstants.CLIENTES)
@Tag(name = "Cliente", description = "Cliente de produtos")
public interface IclienteResource {
	
	@Operation(summary = "Clientes", description = "Serviço Get para lista Cliente.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List Categoria encontrada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }),
			@ApiResponse(responseCode = "404", description = "Lista Categoria não encontrada", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@GetMapping()
	ResponseEntity<List<ClienteDTO>> findAll();
	
	@Operation(summary = "Buscar Cliente", description = "Serviço Get para obter Cliente.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@GetMapping(value = "/{id}")
	ResponseEntity<Cliente> find(@PathVariable Integer id);
	
	
	@Operation(summary = "Criar Cliente", description = "Serviço Post para Criar Cliente.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Cliente criada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@PostMapping
	ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDTO);
	
	@Operation(summary = "Atualizar Cliente", description = "Serviço Put para atualizar Cliente.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Cliente atualizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrada", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@PutMapping(value = "/{id}")
	ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id); 

	@Operation(summary = "Deletar Cliente", description = "Serviço delete para deletar Cliente.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Cliente deletada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrada", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> delete(@PathVariable Integer id);
	
	@Operation(summary = "Clientes paginada", description = "Serviço Get para lista Cliente paginada.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List Cliente paginada encontrada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }),
			@ApiResponse(responseCode = "404", description = "Lista Cliente não encontrada", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@GetMapping(value = "/page")
	ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction);

}
