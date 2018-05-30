package io.github.joyoungc.swagger.api.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.joyoungc.common.Constants;
import io.github.joyoungc.swagger.api.product.model.ProductDTO;
import io.github.joyoungc.swagger.api.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { Constants.API_TAG_PRODUCT })
@RestController
@RequestMapping("/mobile/products")
public class ProductController {

	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@ApiOperation(value = Constants.PRODUCT + "조회")
	@GetMapping("/{productId}")
	public ProductDTO.Response getProduct(@PathVariable String productId) {
		return productService.getProduct(productId);
	}

	@ApiOperation(value = Constants.PRODUCT + "목록조회")
	@GetMapping
	public List<ProductDTO.Response> selectProducts() {
		return productService.selectProducts();
	}

	@ApiOperation(value = Constants.PRODUCT + "등록")
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ProductDTO.Response createProduct(@RequestBody @Validated ProductDTO.Create product) {
		return productService.createProduct(product);
	}

	@ApiOperation(value = Constants.PRODUCT + "수정")
	@PutMapping("/{productId}")
	public void updateProduct(@PathVariable String productId, @RequestBody @Validated ProductDTO.Update product) {
		product.setProductId(productId);
		productService.updateProduct(product);
	}

	@ApiOperation(value = Constants.PRODUCT + "삭제")
	@DeleteMapping("/{productId}")
	public void deleteProduct(@PathVariable String productId) {
		productService.deleteProduct(productId);
	}

}
