package io.github.joyoungc.jpa.product.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.joyoungc.jpa.product.ProductRepository;
import io.github.joyoungc.jpa.product.model.Product;
import io.github.joyoungc.jpa.product.model.ProductDTO;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ProductDTO.Response createProduct(ProductDTO.Create dto) {
		Product product = modelMapper.map(dto, Product.class);
		Product save = repository.save(product);
		System.out.println(save.toString());
		return modelMapper.map(save, ProductDTO.Response.class);
	}
	
	public List<ProductDTO.Response> selectProducts() {
		Type destinationType = new TypeToken<List<ProductDTO.Response>>() {}.getType();	
		return modelMapper.map(repository.findAll(), destinationType);
	}

}
