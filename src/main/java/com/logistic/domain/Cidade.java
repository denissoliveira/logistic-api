package com.logistic.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cidade extends BaseAudit {
	
	private static final long serialVersionUID = 4887092968810914134L;

	public Cidade() {}
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="estado_id")
	private Estado estado;
	
	//Padrão builder
	public static class Builder {
		
		//Requerido
		private final Integer id;
		
		//Opcional
		private String nome;
		private Estado estado;
		
		public Builder(Integer id) {
			this.id = id;
		}
		
		//Opcional
		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}
		
		public Builder estado(Estado estado) {
			this.estado = estado;
			return this;
		}
		
		public Cidade build() {
			return new Cidade(this);
		}
	}
	
	private Cidade(Builder builder) {
		this.setId(builder.id);
		this.nome = builder.nome;
		this.estado = builder.estado;
	}

	//Gets e Sets
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cidade [id" + this.getId() + ", nome=" + nome + ", estado=" + estado + "]";
	}
	
}
