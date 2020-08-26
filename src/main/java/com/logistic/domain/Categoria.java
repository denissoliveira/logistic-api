package com.logistic.domain;

import java.io.Serializable;

public class Categoria implements Serializable{
	
	public Categoria() {}
	
	private static final long serialVersionUID = 1L;
	
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
		
		public Categoria builder() {
			return new Categoria(this);
		}
	}
	
	private Categoria(Builder builder) {
		id = builder.id;
		nome = builder.nome;
	}
	
	//Gets e Sets
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + "]";
	}
	
}
