package com.educandoweb.course.resourses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.User;

@RestController // recurso web implementado por um controlador Rest// @ quer dizer anotation
@RequestMapping(value = "/users") // dar um nome ao recurso, usar o mesmo da classe
public class UserResourse {// disponibiliza recursos web correspondente a entidade User
	// para testar o recurso Rest foi criado um end point para acessar os usuario
	// para usar o Long da variavel na classe User o java solicita colocar o "id +
	// L"
	// REsponseEntity é um tipo especifico para retorno de resposta do Spring Boot
	// <T> é um tipo generics, este tipo é da classe User
	@GetMapping //para indicar que é um metodo que responde o Rest do HTTP é inserido um Anotation	
	public ResponseEntity<User> findAll() {
		User teste = new User(1L, "Marco", "m@m.com", "123456798", "123465798");//Cria um objeto atraves do contrutor
		return ResponseEntity.ok(teste);
		//utiliza o REsponseEntity para enviar a resposta do Rest
		//ok para indicar a resposta OK
		//body indica o corpo do retorno

	}

}
