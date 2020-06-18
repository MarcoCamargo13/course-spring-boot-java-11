package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.educandoweb.course.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity // indica que a classe é uma entidade
@Table(name = "tb_order") // dar um nome a tabela para não conflitar com o comando do SQL order
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;// para tranformar o codi em bytes para trafegr na rede

	// private OrderStatus orderStatus;//declaração da classe orderStatus do tipo
	// enum
	private Integer orderStatus;// declarado integer por causa da modificação de Enum

	@Id // indica que e uma chave em baixo auto incrementavel
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// anottation para formatar o Json
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'ss:mm:ss'Z'", timezone = "GMT")
	private Instant moment;// APARTIR do Java 8 apareceu para substituir o date

	@ManyToOne // no pdf indica que a classe order tem muitos pedidos para um cliente
	@JoinColumn(name = "client_id") // colocando um nome para a chave estrangeira
	private User client;

	@OneToMany(mappedBy = "id.order") // um pedido para varios itens i id.item é que tem os pedidos
	private Set<OrderItem> items = new HashSet<>(); // instanciando os items porque o id do OrdemItem possui o id +
													// produtos e pedidos

	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		this.id = id;
		this.moment = moment;
		// this.orderStatus = orderStatus;//declaração de enum, os valores da classe são
		// fixos utilizando a seguencia declarada, podendo acaretar problemas no futuro
		// com manutenção
		this.setOrderStatus(orderStatus);
		this.client = client;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public OrderStatus getOrderStatus() {
		// return OrderStatus;
		return OrderStatus.valueOf(orderStatus);// para converter orderStatus para inteiro, depois da alteração
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null)// verificar se não é null
			this.orderStatus = orderStatus.getCode();// pegar o inteiro do Order Status
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
