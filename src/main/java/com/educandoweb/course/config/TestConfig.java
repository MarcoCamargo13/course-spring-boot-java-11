package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.Payment;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.enums.OrderStatus;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderItemRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.ProductRepository;
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

	@Autowired // declara a de pencia e associar a instancia no TestConfig
	private OrderRepository orderRepository;// declaração de atributo

	@Autowired // declara a depencia e associar a instancia no TestConfig
	private CategoryRepository categoryRepository;// declaração de atributo

	@Autowired // declara a depencia e associar a instancia no TestConfig
	private ProductRepository productRepository;// declaração de atributo

	@Autowired // declara a depencia e associar a instancia no TestConfig
	private OrderItemRepository orderItemRepository;// declaração de atributo

	@Override // implementação do CommandLineRunner
	public void run(String... args) throws Exception {// tudo que colocar aqui na inicialização subira par ao BD
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		// grava no BD na inicialização chama o metodo UserRepository
		userRepository.saveAll(Arrays.asList(u1, u2));// passa uma lista de objeto para o BD

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.DELIVERED, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.CANCELED, u1);
		// grava no BD na inicialização chama o metodo UserRepository
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));// passa uma lista de objeto para o BD
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));// passa uma lista de objeto para o BD
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));// passa uma lista de objeto para o BD
		
		p1.getCategories().add(cat2);//criando as associaçoes com os produtos 
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);//ASSOCIOU O PEDIDO 1 COM O PAGAMENTO 1 DEWPOSI SALVAR O PEDIDO
		
		orderRepository.save(o1);
	}
}
