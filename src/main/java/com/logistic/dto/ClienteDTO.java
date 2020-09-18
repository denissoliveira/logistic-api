package com.logistic.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.logistic.domain.Cliente;

public class ClienteDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public ClienteDTO() {}
	
	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome(); 
		this.email = obj.getEmail();
	}
	
	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Size(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	//Padrão builder
	public static class Builder {
		
		//Requerido
		private final Integer id;
		private final String nome;
		private final String email;
		
		public Builder(Integer id, String nome, String email) {
			this.id = id;
			this.nome = nome;
			this.email = email;
		}
		
		public Builder(Cliente obj) {
			this.id = obj.getId();
			this.nome = obj.getNome(); 
			this.email = obj.getEmail();
		}
		
		public ClienteDTO build() {
			return new ClienteDTO(this);
		}
	}
	
	private ClienteDTO(Builder builder) {
		id = builder.id;
		nome = builder.nome;
		email = builder.email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
