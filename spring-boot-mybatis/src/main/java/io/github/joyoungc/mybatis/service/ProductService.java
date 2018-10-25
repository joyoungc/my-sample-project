package io.github.joyoungc.mybatis.service;

import java.util.List;

import io.github.joyoungc.common.CommonError;
import io.github.joyoungc.common.exception.ApplicationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.joyoungc.common.model.Product;
import io.github.joyoungc.mybatis.mapper.ProductMapper;

@Service
public class ProductService {

	private final ProductMapper productMapper;
	
	public ProductService(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	public Product getProduct(String productId) {
		Product product = productMapper.getProduct(productId);
		if (product == null)
			throw new ApplicationException(CommonError.COMMON_NOT_FOUND);
		return product;
	}

	public List<Product> selectProducts() {
		List<Product> list = productMapper.selectProducts();
		if (list == null)
			throw new ApplicationException(CommonError.COMMON_NOT_FOUND);
		return list;
	}

	@Transactional
	public Integer createProduct(Product product) {
		return productMapper.createProduct(product);
	}

	@Transactional
	public void updateProduct(Product product) {
		int result = productMapper.updateProduct(product);
		if (result < 1)
			throw new ApplicationException(CommonError.COMMON_NOT_FOUND);
	}

	@Transactional
	public Integer deleteProduct(String productId) {
		int result = productMapper.deleteProduct(productId);
		if (result < 1)
			throw new ApplicationException(CommonError.COMMON_NOT_FOUND);
		return result;
	}

}
