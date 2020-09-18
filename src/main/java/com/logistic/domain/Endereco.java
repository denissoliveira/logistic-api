package com.logistic.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Endereco extends BaseAudit {

	private static final long serialVersionUID = 1L;
	
	public Endereco() {}

	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="cidade_id")
	private Cidade cidade;
	
	//Padrão builder
	public static class Builder {
			
		//Requerido
		private final Integer id;
		private final String logradouro;
		private final String numero;
		private final String complemento;
		private final String bairro;
		private final String cep;
		private final Cidade cidade;
		
		//Opcional
		private Cliente cliente;
		
		public Builder(Integer id, String logradouro, String numero, String complemento, String bairro, String cep, Cidade cidade) {
			this.id = id;
			this.logradouro = logradouro;
			this.numero = numero;
			this.complemento = complemento;
			this.bairro = bairro;
			this.cep = cep;
			this.cidade = cidade;
		}
		
		public Builder setCliente(Cliente cliente) {
			this.cliente = cliente;
			return this;
		}
		
		public Endereco builder() {
			return new Endereco(this);
		}
	}
	
	private Endereco(Builder builder) {
		this.setId(builder.id);
		this.logradouro = builder.logradouro;
		this.numero = builder.numero;
		this.complemento = builder.complemento;
		this.bairro = builder.bairro;
		this.cep = builder.cep;
		this.cliente = builder.cliente;
		this.cidade = builder.cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString() {
		return "Endereco [id" + this.getId() + ", cep=" + cep + ", cliente=" + cliente + "]";
	}
	
}
