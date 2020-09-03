package com.logistic.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.logistic.contants.IConstants;
import com.logistic.domain.Categoria;
import com.logistic.dto.CategoriaDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping(value = IConstants.CATEGORIAS)
@Tag(name = "Categoria", description = "Categoria de produtos")
public interface IcategoriaResource {
	
	@Operation(summary = "Categorias", description = "Serviço Get para lista Categoria.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List Categoria encontrada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) }),
			@ApiResponse(responseCode = "404", description = "Lista Categoria não encontrada", content = @Content)
	})
	@GetMapping()
	ResponseEntity<List<CategoriaDTO>> findAll();
	
	
	@Operation(summary = "Buscar Categoria", description = "Serviço Get para obter Categoria.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categoria encontrada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) }),
			@ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
	})
	@GetMapping(value = "/{id}")
	ResponseEntity<Categoria> find(@PathVariable Integer id);
	
	@Operation(summary = "Criar Categoria", description = "Serviço Post para Criar Categoria.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Categoria criada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) })
	})
	@PostMapping
	ResponseEntity<Void> insert(@RequestBody Categoria obj);
	
	@Operation(summary = "Atualizar Categoria", description = "Serviço Put para atualizar Categoria.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Categoria atualizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) }),
			@ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
	})
	@PutMapping(value = "/{id}")
	ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id); 

	@Operation(summary = "Deletar Categoria", description = "Serviço delete para deletar Categoria.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Categoria deletada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) }),
			@ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
	})
	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> delete(@PathVariable Integer id);
}
