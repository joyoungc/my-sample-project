package io.github.joyoungc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import io.github.joyoungc.mybatis.model.Product;

@Mapper
public interface ProductMapper {

	Product getProduct(String productId);

	List<Product> selectProducts();

	Integer createProduct(Product product);

	Integer updateProduct(Product product);

	Integer deleteProduct(String productId);
}
