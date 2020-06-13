package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.User;

//criando um interface para extender JpaRepositories, responsavel por trabalhar com a entidade User
//JpaRepository<T, ID> T tipo da entidade (User) -> tipo da chave  ID long
//não precisa implementar porque o spring ja possui uma ferramenta para isso
public interface UserRepository extends JpaRepository<User, Long>{

}
