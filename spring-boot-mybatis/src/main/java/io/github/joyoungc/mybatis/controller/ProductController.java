package io.github.joyoungc.mybatis.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.joyoungc.common.model.Product;
import io.github.joyoungc.mybatis.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable String productId) {
        return productService.getProduct(productId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> selectProducts() {
        return productService.selectProducts();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createProduct(@RequestBody @Valid Product product) {
        productService.createProduct(product);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    public void updateProduct(@PathVariable String productId, @RequestBody @Validated Product product) {
        product.setProductId(productId);
        productService.updateProduct(product);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
    }

}
