package io.github.joyoungc.swagger.api.product.service;

import java.util.List;

import io.github.joyoungc.swagger.api.product.model.Product;
import io.github.joyoungc.swagger.api.product.model.RequestProduct;

public interface ProductService {
	
	Product getProduct(String productId);
	
	List<Product> selectProducts();	
	
	Product createProduct(RequestProduct product);
	
	void updateProduct(RequestProduct product);
	
	void deleteProduct(String productId);

}
