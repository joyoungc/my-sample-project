package io.github.joyoungc.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.joyoungc.redis.model.Product;
import io.github.joyoungc.redis.repository.ProductRepository;

@SpringBootApplication
public class SpringBootRedisApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisApplication.class, args);
	}
	
	@Autowired
	ProductRepository repository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("#### Spring is started in @SpringBootApplication ####");
		
		Product product = new Product();
		product.setProductId("1");
		product.setProductName("레디스");
		
		repository.save(product);
		
		repository.findAll().forEach(x -> {
			System.out.println("## 상품 아이디 : " + x.getProductId());
			System.out.println("## 상품명 :  " + x.getProductName());
		});
	}
}
