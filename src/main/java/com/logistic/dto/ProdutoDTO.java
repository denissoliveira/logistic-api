package com.logistic.dto;

import java.io.Serializable;

import com.logistic.domain.Produto;

public class ProdutoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public ProdutoDTO() {}
	
	private Integer id;
	private String nome;
	private Double preco;
	
	//Padrão builder
	public static class Builder {
		
		//Requerido
		private final Integer id;
		private final String nome;
		private final Double preco;
		
		public Builder(Integer id, String nome, Double preco) {
			this.id = id;
			this.nome = nome;
			this.preco = preco;
		}
		
		public Builder(Produto obj) {
			this.id = obj.getId();
			this.nome = obj.getNome(); 
			this.preco = obj.getPreco();
		}
		
		public ProdutoDTO build() {
			return new ProdutoDTO(this);
		}
	}
	
	private ProdutoDTO(Builder builder) {
		id = builder.id;
		nome = builder.nome;
		preco = builder.preco;
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
}
