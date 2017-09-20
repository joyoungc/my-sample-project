package io.github.joyoungc.jpa.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.joyoungc.jpa.product.model.ProductDTO;
import io.github.joyoungc.jpa.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ProductDTO.Response createProduct(@RequestBody @Valid ProductDTO.Create product) {
		return productService.createProduct(product);
	}
	
	@GetMapping
	public List<ProductDTO.Response> selectProducts() {
		return productService.selectProducts();
	}

}
