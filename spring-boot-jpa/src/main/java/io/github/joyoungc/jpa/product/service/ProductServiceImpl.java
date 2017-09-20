package io.github.joyoungc.jpa.product.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.joyoungc.common.service.ProductService;
import io.github.joyoungc.jpa.product.ProductRepository;
import io.github.joyoungc.jpa.product.model.Product;
import io.github.joyoungc.jpa.product.model.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService<ProductDTO.Create, ProductDTO.Response, ProductDTO.Update> {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDTO.Response getProduct(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDTO.Response> selectProducts() {
		Type destinationType = new TypeToken<List<ProductDTO.Response>>() {}.getType();	
		return modelMapper.map(repository.findAll(), destinationType);
	}

	@Override
	public ProductDTO.Response createProduct(ProductDTO.Create dto) {
		Product product = modelMapper.map(dto, Product.class);
		Product save = repository.save(product);
		return modelMapper.map(save, ProductDTO.Response.class);
	}

	@Override
	public void updateProduct(ProductDTO.Update product) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteProduct(String productId) {
		// TODO Auto-generated method stub
	}

}
