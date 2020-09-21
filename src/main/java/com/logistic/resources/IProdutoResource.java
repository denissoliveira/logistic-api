package com.logistic.resources;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.logistic.contants.IConstants;
import com.logistic.domain.Produto;
import com.logistic.dto.ProdutoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping(value = IConstants.PRODUTOS)
@Tag(name = "Produto", description = "Produtos de produto")
public interface IProdutoResource {
	
	@Operation(summary = "Buscar Produto", description = "Serviço Get para obter Produto.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto encontrada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) }),
			@ApiResponse(responseCode = "404", description = "Produto não encontrada", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@GetMapping(value = "/{id}")
	ResponseEntity<Produto> find(@PathVariable Integer id);
	
	@Operation(summary = "Produtos paginada", description = "Serviço Get para lista Produto paginada.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List Produto paginada encontrada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) }),
			@ApiResponse(responseCode = "404", description = "Lista Produto não encontrada", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@GetMapping(value = "/page")
	ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction);

}
