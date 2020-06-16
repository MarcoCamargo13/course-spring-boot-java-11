package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Order;

//criando um interface para extender JpaRepositories, responsavel por trabalhar com a entidade order
//JpaRepository<T, ID> T tipo da entidade (order) -> tipo da chave  ID long
//não precisa implementar porque o spring ja possui uma ferramenta para isso
//isso é uma injeção de dependicia
public interface OrderRepository extends JpaRepository<Order, Long>{

}
