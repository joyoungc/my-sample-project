package io.github.joyoungc.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.joyoungc.common.exception.NoDataFoundException;
import io.github.joyoungc.common.model.Product;
import io.github.joyoungc.mybatis.mapper.ProductMapper;

@Service
public class ProductService {

	@Autowired
	private ProductMapper productMapper;

	public Product getProduct(String productId) {
		Product product = productMapper.getProduct(productId);
		if (product == null)
			throw new NoDataFoundException();
		return product;
	}

	public List<Product> selectProducts() {
		List<Product> list = productMapper.selectProducts();
		if (list == null)
			throw new NoDataFoundException();
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
			throw new NoDataFoundException();
	}

	@Transactional
	public Integer deleteProduct(String productId) {
		int result = productMapper.deleteProduct(productId);
		if (result < 1)
			throw new NoDataFoundException();
		return result;
	}

}
