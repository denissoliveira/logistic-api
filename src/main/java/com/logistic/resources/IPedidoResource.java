package com.logistic.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.logistic.contants.IConstants;
import com.logistic.domain.T;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping(value = IConstants.PEDIDOS)
@Tag(name = "Pedido", description = "Pedidos de produto")
public interface IPedidoResource {
	
	@Operation(summary = "Buscar Pedido", description = "Serviço Get para obter Pedido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pedido encontrada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = T.class)) }),
			@ApiResponse(responseCode = "404", description = "Pedido não encontrada", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@GetMapping(value = "/{id}")
	ResponseEntity<T> find(@PathVariable Integer id);

}
