package com.educandoweb.course.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.educandoweb.course.entities.pk.OrdemItemPk;

@Entity
@Table(name = "tb_ordem_item")
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrdemItemPk id; // atributo identificador da chave primaria ---id composto

	private Integer quantity;
	private Double price;

	public OrderItem() {

	}

	public OrderItem(Product product, Order order, Integer quantity, Double price) {// argumentos
		super();
		id.setOrder(order); // com isso instancia o oerderItem com id, produto, order, quantity e price
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}

	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Product getProduct() {
		return id.getProduct();
	}

	public void setOrder(Order order) {
		id.setOrder(order);
	}

	public Order getOrder() {
		return id.getOrder();
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

}
