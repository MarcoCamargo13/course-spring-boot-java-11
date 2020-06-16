package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.repositories.CategoryRepository;

@Service // para indicar o registro no compnente do Spring para funcionar, temos tb Ex.
			// @Component /@REpository
public class CategoryService {// criado uma classe de serviço para cuidar das transações
	@Autowired // para fazer uma injeção de dependencia de forma clara
	private CategoryRepository repository;// injeção de dependecia

	// metodo para retornar todos os usuarios de uma ista do tipo Order
	public List<Category> findAll() {// criado um metodo que repassa a chamada para a camada findAll do
									// OrderRepository
		return repository.findAll();
	}

	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);//Optional é um objeto tipo <Order>
		return obj.get();
	}
}
