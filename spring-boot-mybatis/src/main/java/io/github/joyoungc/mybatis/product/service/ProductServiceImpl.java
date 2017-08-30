package io.github.joyoungc.mybatis.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.joyoungc.common.exception.NoDataFoundException;
import io.github.joyoungc.common.model.Product;
import io.github.joyoungc.common.service.ProductService;
import io.github.joyoungc.mybatis.product.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMapper productMapper;

	@Override
	public Product getProduct(String productId) {
		Product product = productMapper.getProduct(productId);
		if (product == null)
			throw new NoDataFoundException();
		return product;
	}

	@Override
	public List<Product> selectProducts() {
		List<Product> list = productMapper.selectProducts();
		if (list == null)
			throw new NoDataFoundException();
		return list;
	}

	@Override
	@Transactional	
	public void createProduct(Product product) {
		productMapper.createProduct(product);
	}

	@Override
	@Transactional
	public void updateProduct(Product product) {
		int result = productMapper.updateProduct(product);
		if (result < 1)
			throw new NoDataFoundException();
	}

	@Override
	@Transactional
	public void deleteProduct(String productId) {
		int result = productMapper.deleteProduct(productId);
		if (result < 1)
			throw new NoDataFoundException();
	}

}
