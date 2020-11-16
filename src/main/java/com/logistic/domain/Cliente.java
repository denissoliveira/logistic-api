package com.logistic.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logistic.domain.enums.TipoCliente;

@Entity
public class Cliente extends BaseAudit {
	
	private static final long serialVersionUID = 1434287474084177678L;

	public Cliente() {}
	
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;
	@JsonIgnore	private String senha;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name = "telefone")
	private Set<String> telefones = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();
	
	//Padrão builder
	public static class Builder {
		
		//Requerido
		private final Integer id;
		private final String nome;
		private final String email;
		private final String cpfOuCnpj;
		private String senha;
		private TipoCliente tipo;
		
		//Opcional
		private List<Endereco> enderecos = new ArrayList<>();
		private Set<String> telefones = new HashSet<>();
		private List<Pedido> pedidos = new ArrayList<>();
		
		public Builder(Integer id, String nome, String email, String cpfOuCnpj, String senha, TipoCliente tipo) {
			this.id = id;
			this.nome = nome;
			this.email = email;
			this.cpfOuCnpj = cpfOuCnpj;
			this.senha = senha;
			this.tipo = tipo == null ? null : tipo;
		}
		
		public Builder setPedidos(List<Pedido> pedidos) {
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
		
		public Cliente build() {
			return new Cliente(this);
		}
	}
	
	private Cliente(Builder builder) {
		this.setId(builder.id);
		this.nome = builder.nome;
		this.email = builder.email;
		this.cpfOuCnpj = builder.cpfOuCnpj;
		this.senha = builder.senha;
		this.tipo = builder.tipo == null ? null : builder.tipo.getCod();
		this.enderecos = builder.enderecos;
		this.telefones = builder.telefones;
		this.pedidos = builder.pedidos;
	}

	//Gets e Sets
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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Cliente [id" + this.getId() + ", nome=" + nome + ", email=" + email + ", cpfOuCnpj=" + cpfOuCnpj + ", tipo=" + tipo + "]";
	}

}
