package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

//anotações indicando para JPA de como ira converter os objetos para o banco relacional
//sempre depender da expecificação nunda da implementação (o maven cria os import dele)
//para testar a configuração do H2 pom + application.properties , ir no navegador http://localhost:8080/h2-console
@Entity //classe é uma entidade
@Table(name = "tb_user")//colocando um anome a atabela
public class User implements Serializable {// gera cadeia em bytes para trafegar em redes e criar arquivos

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // ANOTATION PARA INDICAR para JPA que o ID é autoincrementavel,
	private Long id;// para o JPA ela é uma chave numerica e autoincrementavel
	private String name;
	private String email;
	private String phone;
	private String password;

	@JsonIgnore
	//(mappedBy = "client") --> para acessar os pedido opcional, indica que foi mapeado a tabela de cliente
	@OneToMany(mappedBy = "client") //a anotação é para indicar para o banco que é muito para 1(varios pedidos para um cliente)
	private List<Order> orders = new ArrayList<>();// foi criada uma associação de 1 ..muitos, foi criado um get
													// somente(pq com o set trocariamos a lista)

	public User() {// construtor vazio obrigatorio em Spring Boot

	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {// hash code para comparação de objetos
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {// define uma estruta basica de impressão dos objetos, senão aparece o endereço
								// de memoria
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password=" + password
				+ "]";
	}

}
