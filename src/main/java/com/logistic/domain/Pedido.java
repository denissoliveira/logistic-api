package com.logistic.domain;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pedido extends BaseAudit {
	
	private static final long serialVersionUID = 1L;

	public Pedido() {}

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
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
	
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();
	
	public double getValorTotal() {
		double soma = 0.0;
		for (ItemPedido ip : itens) {
			soma = soma + ip.getSubTotal();
		}
		return soma;
	}
	
	//Padrão builder
	public static class Builder {
		
		//Requerido
		private final Integer id;
		private Date instante;
		private Cliente cliente;
		private Endereco enderecoDeEntrega;
		
		//Opcional
		private Pagamento pagamento;
		private Set<ItemPedido> itens = new HashSet<>();
		
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
		
		public Builder setItens(Set<ItemPedido> itens) {
			this.itens = itens;
			return this;
		}
		
		public Pedido builder() {
			return new Pedido(this);
		}
	}
	
	private Pedido(Builder builder) {
		this.setId(builder.id);
		instante = builder.instante;
		pagamento = builder.pagamento;
		cliente = builder.cliente;
		enderecoDeEntrega = builder.enderecoDeEntrega;
		itens = builder.itens;
	}
	
	//Gets and Sets
	@JsonIgnore
	public List<Produto> getProdutos() {
		List<Produto> lista = new ArrayList<>();
		for (ItemPedido item : itens) {
			lista.add(item.getProduto());
		}
		return lista;
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

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(getId());
		builder.append(", Instante: ");
		builder.append(sdf.format(getInstante()));
		builder.append(", Cliente: ");
		builder.append(getCliente().getNome());
		builder.append(", Situação do pagamento: ");
		builder.append(getPagamento().getEstado().getDescricao());
		builder.append("\nDetalhes:\n");
		for (ItemPedido ip : getItens()) {
			builder.append(ip.toString());
		}
		builder.append("Valor total: ");
		builder.append(nf.format(getValorTotal()));
		return builder.toString();
	}
	
}
