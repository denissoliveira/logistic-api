package com.logistic.dto;

import java.io.Serializable;

import com.logistic.domain.Categoria;

public class CategoriaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public CategoriaDTO() {}
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome(); 
	}
	
	private Integer id;
	private String nome;
	
	//Padrão builder
	public static class Builder {
		
		//Requerido
		private final Integer id;
		private final String nome;
		
		public Builder(Integer id, String nome) {
			this.id = id;
			this.nome = nome;
		}
		
		public Builder(Categoria obj) {
			this.id = obj.getId();
			this.nome = obj.getNome(); 
		}
		
		public CategoriaDTO builder() {
			return new CategoriaDTO(this);
		}
	}
	
	private CategoriaDTO(Builder builder) {
		id = builder.id;
		nome = builder.nome;
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
	
}
