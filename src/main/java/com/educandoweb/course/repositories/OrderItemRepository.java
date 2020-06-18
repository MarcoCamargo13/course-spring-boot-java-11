package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.OrderItem;

//criando um interface para extender JpaRepositories, responsavel por trabalhar com a entidade Category
//JpaRepository<T, ID> T tipo da entidade (Category) -> tipo da chave  ID long
//não precisa implementar porque o spring ja possui uma ferramenta para isso
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
