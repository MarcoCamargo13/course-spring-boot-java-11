package com.educandoweb.course.resourses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController // recurso web implementado por um controlador Rest// @ quer dizer anotation
@RequestMapping(value = "/users") // dar um nome ao recurso, usar o mesmo da classe
public class UserResourse {// disponibiliza recursos web correspondente a entidade User
	@Autowired // para o Spring fazer a injeção de dependecia automatico
	private UserService service; // dependencia do serviçe

	// para indicar que é um metodo que responde o Rest do HTTP é inserido um
	@GetMapping // Anotation
	public ResponseEntity<List<User>> findAll() {
		// o metodo findAll recebe uma lista de User
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	// CRIANDO UM END POINT PARA BUSCAR UM SUSUARIO POR ID
	@GetMapping(value = "/{id}") // vai indicar que o get recebe um id na URL
	// @PathVariable para o Spring aceitar que passar como parametro(value =
	// "/{id}") do tipo Long
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);// metodo para retorna um usuario pelo URL

	}

}
