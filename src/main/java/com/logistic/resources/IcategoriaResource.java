package com.logistic.resources;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.logistic.contants.IConstants;
import com.logistic.domain.Categoria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping(value = IConstants.CATEGORIA)
@Tag(name = "Categoria", description = "Categoria de produtos")
public interface IcategoriaResource {
	
	@Operation(summary = "Listar Categorias", description = "Serviço Get para obter Listar de Categorias.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de categoria encontrada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) }),
			@ApiResponse(responseCode = "404", description = "Lista não encontrada", content = @Content)
	})
	@GetMapping
	List<Categoria> listar();

}
