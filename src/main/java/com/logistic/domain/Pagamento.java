package com.logistic.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logistic.domain.enums.EstadoPagamento;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)// Cria uma tabela pra cada extendido desta classe
public abstract class Pagamento implements Serializable {
	
	public Pagamento() {}
	
	public Pagamento(Integer id, Integer estado, T pedido) {
		super();
		this.id = id;
		this.estado = estado;
		this.pedido = pedido;
	}

	private static final long serialVersionUID = 1L;
	
	@Id //Vai manter o mesmo ID de pedido
	private Integer id;
	private Integer estado;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "pedido_id") //Vai manter o mesmo ID de pedido
	@MapsId //Vai manter o mesmo ID de pedido
	private T pedido;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public T getPedido() {
		return pedido;
	}

	public void setPedido(T pedido) {
		this.pedido = pedido;
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
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pagamento [id=" + id + ", estado=" + estado + ", pedido=" + pedido + "]";
	}
	
}
