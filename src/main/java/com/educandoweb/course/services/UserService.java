package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import net.bytebuddy.asm.Advice.Thrown;

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
		Optional<User> obj = repository.findById(id);// Optional é um objeto tipo <User>
		//return obj.get();//antes do tratamento de excessão
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User obj) {// metodo para inserir um novo usuario no BD
		return repository.save(obj); // neste metodo ele ja retorna na inserção o usuario
	}

	public void delete(Long id) {
		try { //tratando a excessão
		repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			//e.printStackTrace();//usado para capturar a exceção
			throw new ResourceNotFoundException(id); //para padronizar as exceções
		}catch (DataIntegrityViolationException e) {
			//e.printStackTrace();//foi feito um teste rodanda a aplicação e deletado usuario 1 para pegar a DataIntegrityViolationException
			throw new DatabaseException(e.getMessage());
			//EXCESssão da camada de servico
		}
	}

	// User porque retona um usuario atualizado
	// nome do metodo de update
	// User Ob um objeto user dom os dados quer serão atualizados
	public User update(Long id, User obj) {
		// entity entidade monitorada pelo jpa
		// getOne vai instanciar um usuario
		// getOne não vai no BD, deixando o obj monitorado pelo JPA e trabalhar para
		// depois enviar
		User entity = repository.getOne(id);
		updateData(entity, obj); // função para atualizar os dados entity baseado no obj
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {// atualizar o entity baseado no obj
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
