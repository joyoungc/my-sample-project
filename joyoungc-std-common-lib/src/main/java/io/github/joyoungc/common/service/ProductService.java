package io.github.joyoungc.common.service;

import java.util.List;

import io.github.joyoungc.common.model.Product;

public interface ProductService {
	
	Product getProduct(String productId);
	
	List<Product> selectProducts();
	
	void createProduct(Product product);
	
	void updateProduct(Product product);
	
	void deleteProduct(String productId);

}
