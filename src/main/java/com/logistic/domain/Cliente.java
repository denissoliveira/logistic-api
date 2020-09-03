package com.logistic.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logistic.domain.enums.TipoCliente;

@Entity
public class Cliente implements Serializable{
	
	public Cliente() {}
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;
	
	@OneToMany(mappedBy = "cliente")
	private List<Endereco> enderecos = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name = "telefone")
	private Set<String> telefones = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<T> pedidos = new ArrayList<>();
	
	//Padrão builder
	public static class Builder {
		
		//Requerido
		private final Integer id;
		private final String nome;
		private final String email;
		private final String cpfOuCnpj;
		private TipoCliente tipo;
		
		//Opcional
		private List<Endereco> enderecos = new ArrayList<>();
		private Set<String> telefones = new HashSet<>();
		private List<T> pedidos = new ArrayList<>();
		
		public Builder(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
			this.id = id;
			this.nome = nome;
			this.email = email;
			this.cpfOuCnpj = cpfOuCnpj;
			this.tipo = tipo;
		}
		
		public Builder setPedidos(List<T> pedidos) {
			this.pedidos = pedidos;
			return this;
		}
		
		public Builder setEnderecos(List<Endereco> enderecos) {
			this.enderecos = enderecos;
			return this;
		}
		
		public Builder setTelefones(Set<String> telefones) {
			this.telefones = telefones;
			return this;
		}
		
		public Cliente builder() {
			return new Cliente(this);
		}
	}
	
	private Cliente(Builder builder) {
		id = builder.id;
		nome = builder.nome;
		email = builder.email;
		cpfOuCnpj = builder.cpfOuCnpj;
		tipo = builder.tipo.getCod();
		enderecos = builder.enderecos;
		telefones = builder.telefones;
		pedidos = builder.pedidos;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public List<T> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<T> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", email=" + email + ", cpfOuCnpj=" + cpfOuCnpj + ", tipo="
				+ tipo + "]";
	}
	
}
