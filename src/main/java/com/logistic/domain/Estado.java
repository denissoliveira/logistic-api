package com.logistic.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Estado extends BaseAudit {
	
	private static final long serialVersionUID = 1L;

	public Estado() {}
	
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy="estado")
	private List<Cidade> cidades = new ArrayList<>();
	
	//Padrão builder
	public static class Builder {
		
		//Requerido
		private final Integer id;
		private final String nome;
		
		//Opcional
		private List<Cidade> cidades = new ArrayList<>();
		
		public Builder(Integer id, String nome) {
			this.id = id;
			this.nome = nome;
		}
		
		//Opcional
		public Builder cidades(List<Cidade> cidades) {
			this.cidades = cidades;
			return this;
		}
		
		public Estado builder() {
			return new Estado(this);
		}
	}
	
	private Estado(Builder builder) {
		this.setId(builder.id);
		nome = builder.nome;
		cidades = builder.cidades;
	}

	//Gets e Sets
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	@Override
	public String toString() {
		return "Estado [id" + this.getId() + ", nome=" + nome + ", cidades=" + cidades + "]";
	}

}
