package io.github.joyoungc.jpa.product.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.joyoungc.jpa.product.model.ProductDTO;
import io.github.joyoungc.jpa.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ProductDTO.Response createProduct(@RequestBody @Valid ProductDTO.Create product) {
		return productService.createProduct(product);
	}

	@GetMapping 
	public Page<ProductDTO.Response> selectProducts(@PageableDefault(page = 1, size = 10) Pageable pageable) {
		return productService.selectProducts(pageable);
	}
	
	@GetMapping("/{productId}") 
	public ProductDTO.Response getProduct(@PathVariable String productId) {
		return productService.getProduct(productId);
	}
	
	@PutMapping("/{productId}")
	public ProductDTO.Response updateProduct(@PathVariable String productId, @RequestBody @Valid ProductDTO.Update product) {
		return productService.updateProduct(productId, product);
	}
	
	@DeleteMapping("/{productId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable String productId) {
		productService.deleteProduct(productId);
	}

}
