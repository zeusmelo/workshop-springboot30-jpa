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
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderItemRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRespository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		
		Category cat01 = new Category(null, "Eletronics");
		Category cat02 = new Category(null, "Books");
		Category cat03 = new Category(null, "Computers");
		
		categoryRepository.saveAll(Arrays.asList(cat01,cat02,cat03));
		productRespository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		p1.getCategories().add(cat02);
		p2.getCategories().add(cat01);
		p3.getCategories().add(cat01);
		p4.getCategories().add(cat01);
		p5.getCategories().add(cat03);
		
		productRespository.saveAll(Arrays.asList(p1,p2,p3,p4));
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order order1 = new Order (null, Instant.parse("2019-06-20T19:53:55Z"), u1, OrderStatus.CANCELED );
		Order order2 = new Order (null, Instant.parse("2019-06-20T19:53:33Z"), u2, OrderStatus.DELIVERED );
		Order order3 = new Order (null, Instant.parse("2019-06-20T19:53:17Z"), u1, OrderStatus.SHIPPED);
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(order1,order2,order3));
		
		OrderItem oi1 = new OrderItem(order1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(order1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(order2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(order3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));
		
	}
	
	
	
	
}
