package io.github.joyoungc.swagger.api.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import io.github.joyoungc.common.exception.NoDataFoundException;
import io.github.joyoungc.common.model.Product;
import io.github.joyoungc.common.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	List<Product> products = new ArrayList<>();

	@PostConstruct
	public void init() {
		products.add(new Product("P100001", "Galaxy S7", 800000, "갤럭시S7"));
		products.add(new Product("P100002", "iPhone 7", 950000, "아이폰7"));
		products.add(new Product("P100003", "LG G6", 540000, "엘지G6"));
		products.add(new Product("P100004", "Sony XPERIA XZ Premium", 600000, "소니 엑스페리아XZ"));
	}

	@Override
	public Product getProduct(String productId) {
		return products.stream().filter(p -> p.getProductId().equals(productId)).findFirst()
				.orElseThrow(NoDataFoundException::new);
	}

	@Override
	public List<Product> selectProducts() {
		return products;
	}

	@Override
	public void createProduct(Product product) {
		Product addProduct = new Product(UUID.randomUUID().toString(), product.getProductName(), product.getPrice(), product.getDescription());
		products.add(addProduct);
	}

	@Override
	public void updateProduct(Product product) {
		boolean removed = products.removeIf(p -> p.getProductId().equals(product.getProductId()));
		if (!removed) {
			throw new NoDataFoundException();
		}
		products.add(new Product(product.getProductId(), product.getProductName(), product.getPrice(), product.getDescription()));
		products.sort((o1,o2) -> o1.getProductId().compareTo(o2.getProductId()));
	}

	@Override
	public void deleteProduct(String productId) {
		boolean removed = products.removeIf(p -> p.getProductId().equals(productId));
		if (!removed) {
			throw new NoDataFoundException();
		}
	}

}
