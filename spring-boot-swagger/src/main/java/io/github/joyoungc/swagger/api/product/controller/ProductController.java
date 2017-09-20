package io.github.joyoungc.swagger.api.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.joyoungc.common.Code;
import io.github.joyoungc.common.service.ProductService;
import io.github.joyoungc.swagger.api.product.model.ProductDTO;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mobile/products")
public class ProductController {

	@Autowired
	ProductService<ProductDTO.Create, ProductDTO.Response, ProductDTO.Update> productService;

	@ApiOperation(value = Code.Constants.PRODUCT + "조회", tags = { Code.Constants.API_TAG_PRODUCT })
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public ProductDTO.Response getProduct(@PathVariable String productId) {
		return productService.getProduct(productId);
	}

	@ApiOperation(value = Code.Constants.PRODUCT + "목록조회", tags = { Code.Constants.API_TAG_PRODUCT })
	@RequestMapping(method = RequestMethod.GET)
	public List<ProductDTO.Response> selectProducts() {
		return productService.selectProducts();
	}

	@ApiOperation(value = Code.Constants.PRODUCT + "등록", tags = { Code.Constants.API_TAG_PRODUCT })
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ProductDTO.Response createProduct(@RequestBody @Validated ProductDTO.Create product) {
		return productService.createProduct(product);
	}

	@ApiOperation(value = Code.Constants.PRODUCT + "수정", tags = { Code.Constants.API_TAG_PRODUCT })
	@RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
	public void updateProduct(@PathVariable String productId, @RequestBody @Validated ProductDTO.Update product) {
		product.setProductId(productId);
		productService.updateProduct(product);
	}

	@ApiOperation(value = Code.Constants.PRODUCT + "삭제", tags = { Code.Constants.API_TAG_PRODUCT })
	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable String productId) {
		productService.deleteProduct(productId);
	}

}
