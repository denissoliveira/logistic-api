package com.logistic.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.logistic.contants.IConstants;
import com.logistic.domain.Categoria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping(value = IConstants.CATEGORIAS)
@Tag(name = "Categoria", description = "Categoria de produtos")
public interface IcategoriaResource {
	
	@Operation(summary = "Buscar Categoria", description = "Serviço Get para obter Categoria.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categoria encontrada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) }),
			@ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
	})
	@GetMapping(value = "/{id}")
	ResponseEntity<?> find(@PathVariable Integer id);

}
