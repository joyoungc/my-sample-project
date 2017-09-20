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
import io.github.joyoungc.swagger.api.product.model.RequestProduct;
import io.github.joyoungc.swagger.api.product.model.ResponseProduct;
import io.github.joyoungc.swagger.api.product.model.mapper.ProductModelMapper;
import io.github.joyoungc.swagger.api.product.service.ProductServiceImpl;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mobile/products")
public class ProductController {

	@Autowired
	ProductServiceImpl productService;

	@ApiOperation(value = Code.Constants.PRODUCT + "조회", tags = { Code.Constants.API_TAG_PRODUCT })
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public ResponseProduct getProduct(@PathVariable String productId) {
		return ProductModelMapper.toResponse(productService.getProduct(productId));
	}

	@ApiOperation(value = Code.Constants.PRODUCT + "목록조회", tags = { Code.Constants.API_TAG_PRODUCT })
	@RequestMapping(method = RequestMethod.GET)
	public List<ResponseProduct> selectProducts() {
		return ProductModelMapper.toResponse(productService.selectProducts());
	}

	@ApiOperation(value = Code.Constants.PRODUCT + "등록", tags = { Code.Constants.API_TAG_PRODUCT })
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createProduct(@RequestBody @Validated RequestProduct product) {
		productService.createProduct(product);
	}

	@ApiOperation(value = Code.Constants.PRODUCT + "수정", tags = { Code.Constants.API_TAG_PRODUCT })
	@RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
	public void updateProduct(@PathVariable String productId, @RequestBody @Validated RequestProduct product) {
		product.setProductId(productId);
		productService.updateProduct(product);
	}

	@ApiOperation(value = Code.Constants.PRODUCT + "삭제", tags = { Code.Constants.API_TAG_PRODUCT })
	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable String productId) {
		productService.deleteProduct(productId);
	}

}
