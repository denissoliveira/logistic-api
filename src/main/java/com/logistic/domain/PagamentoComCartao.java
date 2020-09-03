package com.logistic.domain;

import javax.persistence.Entity;

@Entity
public class PagamentoComCartao extends Pagamento{
	
	public PagamentoComCartao() {}
	
	public PagamentoComCartao(Integer id, Integer estado, T pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcelas;

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

	@Override
	public String toString() {
		return "PagamentoComCartao [numeroDeParcelas=" + numeroDeParcelas + "]";
	}
	
}
