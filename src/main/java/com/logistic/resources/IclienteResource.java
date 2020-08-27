package com.logistic.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.logistic.contants.IConstants;
import com.logistic.domain.Cliente;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping(value = IConstants.CLIENTES)
@Tag(name = "Cliente", description = "Cliente de produtos")
public interface IclienteResource {
	
	@Operation(summary = "Buscar Cliente", description = "Serviço Get para obter Cliente.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
	})
	@GetMapping(value = "/{id}")
	ResponseEntity<?> find(@PathVariable Integer id);

}
