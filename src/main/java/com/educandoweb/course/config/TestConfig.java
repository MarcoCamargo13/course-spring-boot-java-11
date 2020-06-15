package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.UserRepository;


@Configuration // indicar para Spring que é uma classe configuração se coloca uma anotation
@Profile("test") // para confirmar ao Spring que uma classe de configuração mai uma anotation
					// igual ao properties
public class TestConfig implements CommandLineRunner {// classe do configuração expecifica do perfil de teste
//classe para popular o BD
//injeção de dependia que o serviço depende de outro a dependencia tem que ser fraca e desacoplada utilizando interface factory, etc
	@Autowired // declara a depencia e associar a instancia no TestConfig
	private UserRepository userRepository;// declaração de atributo
//CommandLineRunner -->é uma interface para quando o Spring for inciado ela start o serviço 
	
	@Autowired // declara a de	pencia e associar a instancia no TestConfig
	private OrderRepository OrderRepository;// declaração de atributo
	
	@Override//implementação do CommandLineRunner
	public void run(String... args) throws Exception {//tudo que colocar aqui na inicialização subira par ao BD
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1);

		//grava no BD na inicialização chama o metodo UserRepository
		userRepository.saveAll(Arrays.asList(u1, u2));//passa uma lista de objeto para o BD
		OrderRepository.saveAll(Arrays.asList(o1, o2, o3));//passa uma lista de objeto para o BD
	}
}
