package com.logistic.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido implements Serializable {
	
	public Pedido() {}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date instante;
	
	//O pedido de ID será o mesmo Pagamento
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco enderecoDeEntrega;
	
	//Padrão builder
	public static class Builder {
		
		//Requerido
		private final Integer id;
		private Date instante;
		private Cliente cliente;
		private Endereco enderecoDeEntrega;
		
		//Opcional
		private Pagamento pagamento;
		
		public Builder(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
			this.id = id;
			this.instante = instante;
			this.cliente = cliente;
			this.enderecoDeEntrega = enderecoDeEntrega;
		}
		
		public Builder setPagamento(Pagamento pagamento) {
			this.pagamento = pagamento;
			return this;
		}
		
		public Pedido builder() {
			return new Pedido(this);
		}
	}
	
	private Pedido(Builder builder) {
		id = builder.id;
		instante = builder.instante;
		pagamento = builder.pagamento;
		cliente = builder.cliente;
		enderecoDeEntrega = builder.enderecoDeEntrega;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", instante=" + instante + ", pagamento=" + pagamento + ", cliente=" + cliente
				+ "]";
	}
	
}