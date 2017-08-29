package io.github.joyoungc.mybatis.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import io.github.joyoungc.common.model.Product;

@Mapper
public interface ProductMapper {
	
	Product getProduct(String productId);
	
	List<Product> selectProducts();
	
	Integer createProduct(Product product);
	
	Integer updateProduct(Product product);
	
	Integer deleteProduct(String productId);
}
