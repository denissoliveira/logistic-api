package com.logistic.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.logistic.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComBoleto") //tipo se ser indentificado pelo json (@type)
public class PagamentoComBoleto extends Pagamento{
	

	private static final long serialVersionUID = 1L;
	public PagamentoComBoleto() {}
	
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataPagamento;

	//Padrão builder
	public static class Builder {
		
		public PagamentoComBoleto build() {
			return new PagamentoComBoleto(this);
		}
	}
	
	private PagamentoComBoleto(Builder builder) {

	}
	
	//Gets and Sets
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Override
	public String toString() {
		return "PagamentoComBoleto [dataVencimento=" + dataVencimento + ", dataPagamento=" + dataPagamento + "]";
	}
	
}
