package com.logistic.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido {
	
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPk id = new ItemPedidoPk();
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	public ItemPedido() {}
	
	//Padrão builder
	public static class Builder {
		
		//Requerido
		private ItemPedidoPk id = new ItemPedidoPk();
		private Double desconto;
		private Integer quantidade;
		private Double preco;

		
		public Builder(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
			this.id.setPedido(pedido);
			this.id.setProduto(produto);
			this.desconto = desconto;
			this.quantidade = quantidade;
			this.preco = preco;
		}
		
		public ItemPedido builder() {
			return new ItemPedido(this);
		}
	}
	
	private ItemPedido(Builder builder) {
		id = builder.id;
		desconto = builder.desconto;
		quantidade = builder.quantidade;
		preco = builder.preco;
	}
	
	public double getSubTotal() {
		return (preco - desconto) * quantidade;
	}
	
	//Obter pedido diretamente
	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	//Obter Produto diretamente
	public Produto getProduto() {
		return id.getProduto();
	}

	public ItemPedidoPk getId() {
		return id;
	}

	public void setId(ItemPedidoPk id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
