package io.github.joyoungc.jpa.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.joyoungc.jpa.product.model.Product;
import io.github.joyoungc.jpa.product.repository.ProductRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository repository;
	
	@Test
	public void testProductSave() {
		Product product = new Product();
		product.setProductName("테스트");
		Product result  = repository.save(product);
		
		assertThat(result).isNotNull();
		
		Optional<Product> findById = repository.findById(result.getProductId());
		assertThat(findById).isNotEmpty();
	}

}
