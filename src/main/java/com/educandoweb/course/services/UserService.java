package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

@Service // para indicar o registro no compnente do Spring para funcionar, temos tb Ex.
			// @Component /@REpository
public class UserService {// criado uma classe de serviço para cuidar das transações
	@Autowired // para fazer uma injeção de dependencia de forma clara
	private UserRepository repository;// injeção de dependecia

	// metodo para retornar todos os usuarios de uma ista do tipo User
	public List<User> findAll() {// criado um metodo que repassa a chamada para a camada findAll do
									// UserRepository
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);//Optional é um objeto tipo <User>
		return obj.get();
	}
	
	public User insert(User obj) {//metodo para inserir um novo usuario no BD
		return repository.save(obj); //neste metodo ele ja retorna na inserção o usuario
	}
}
