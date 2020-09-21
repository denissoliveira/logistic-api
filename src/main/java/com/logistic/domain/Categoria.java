package com.logistic.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Categoria extends BaseAudit {
	
	private static final long serialVersionUID = -7066598196454042117L;
	
	public Categoria() {}

	private String nome;
	
	@ManyToMany(mappedBy="categorias")
	private List<Produto> produtos = new ArrayList<>();
	
	//Padrão builder
	public static class Builder {
		
		//Requerido
		private final Integer id;
		private final String nome;
		
		//optional
		private List<Produto> produtos = new ArrayList<>();
		
		public Builder(Integer id, String nome) {
			this.id = id;
			this.nome = nome;
		}
		
		public Builder produtos(List<Produto> produtos) {
			this.produtos = produtos;
			return this;
		}
		
		public Categoria build() {
			return new Categoria(this);
		}
	}
	
	private Categoria(Builder builder) {
		this.setId(builder.id);
		this.nome = builder.nome;
		this.produtos = builder.produtos;
	}
	
	//Gets e Sets
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Categoria [id" + this.getId() + ", nome=" + nome + ", produtos=" + produtos + "]";
	}
	
}
