package io.github.joyoungc.swagger.api.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import io.github.joyoungc.common.exception.NoDataFoundException;
import io.github.joyoungc.common.mapper.CommonMapper;
import io.github.joyoungc.common.model.Product;
import io.github.joyoungc.swagger.api.product.model.ProductDTO;

@Service
public class ProductService {
	
	List<Product> products = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		products.add(Product.builder().productId("P100001").productName("Galaxy S7").price(800000).description("갤럭시S7").build());
		products.add(Product.builder().productId("P100002").productName("iPhone 7").price(950000).description("아이폰7").build());
		products.add(Product.builder().productId("P100003").productName("LG G6").price(540000).description("엘지G6").build());
		products.add(Product.builder().productId("P100004").productName("Sony XPERIA XZ Premium").price(600000).description("소니 엑스페리아XZ").build());
	}

	public ProductDTO.Response getProduct(String productId) {
		return CommonMapper.toModel(products.stream().filter(p -> p.getProductId().equals(productId)).findFirst()
				.orElseThrow(NoDataFoundException::new), ProductDTO.Response.class);
	}

	public List<ProductDTO.Response> selectProducts() {
		return CommonMapper.toList(products, ProductDTO.Response.class);
	}

	public ProductDTO.Response createProduct(ProductDTO.Create product) {
		Product addProduct = CommonMapper.toModel(product, Product.class);
		addProduct.setProductId(UUID.randomUUID().toString());
		products.add(addProduct);
		return CommonMapper.toModel(addProduct,ProductDTO.Response.class);
	}

	public void updateProduct(ProductDTO.Update product) {
		boolean removed = products.removeIf(p -> p.getProductId().equals(product.getProductId()));
		if (!removed) {
			throw new NoDataFoundException();
		}
		products.add(CommonMapper.toModel(product, Product.class));
		products.sort((o1, o2) -> o1.getProductId().compareTo(o2.getProductId()));
	}

	public void deleteProduct(String productId) {
		boolean removed = products.removeIf(p -> p.getProductId().equals(productId));
		if (!removed) {
			throw new NoDataFoundException();
		}
	}

}
