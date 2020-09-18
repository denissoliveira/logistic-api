package com.logistic.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto extends BaseAudit {
	
	private static final long serialVersionUID = 1L;

	public Produto() {}
	
	private String nome;
	private Double preco;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA",
		joinColumns = @JoinColumn(name = "produto_id"),
		inverseJoinColumns = @JoinColumn(name = "categoria_id")
	)
	private List<Categoria> categorias = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.produto")
	private Set<ItemPedido> itens = new HashSet<>();
	
	//Padrão builder
	public static class Builder {
		
		//Requerido
		private final Integer id;
		private final String nome;
		private final Double preco;
		
		//Opcional
		private List<Categoria> categorias = new ArrayList<>();
		private Set<ItemPedido> itens = new HashSet<>();
		
		public Builder(Integer id, String nome, Double preco) {
			this.id = id;
			this.nome = nome;
			this.preco = preco;
		}
		
		public Builder setCategorias(List<Categoria> categorias) {
			this.categorias = categorias;
			return this;
		}
		
		public Builder setItens(Set<ItemPedido> itens) {
			this.itens = itens;
			return this;
		}
		
		public Produto builder() {
			return new Produto(this);
		}
	}
	
	private Produto(Builder builder) {
		this.setId(builder.id);
		nome = builder.nome;
		preco = builder.preco;
		categorias = builder.categorias;
		itens = builder.itens;
	}

	//Gets e Sets
	@JsonIgnore //Para não precisar serializar quando buscar
	public List<Pedido> getPedidos() {
		List<Pedido> lista = new ArrayList<>();
		for (ItemPedido item : itens) {
			lista.add(item.getPedido());
		}
		return lista;
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

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "Produto [id" + this.getId() + ", nome=" + nome + ", preco=" + preco + ", categorias=" + categorias + "]";
	}
	
}
