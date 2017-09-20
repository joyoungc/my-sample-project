package io.github.joyoungc.swagger.api.product.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.joyoungc.common.exception.NoDataFoundException;
import io.github.joyoungc.common.model.Product;
import io.github.joyoungc.common.service.ProductService;
import io.github.joyoungc.swagger.api.product.model.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService<ProductDTO.Create, ProductDTO.Response, ProductDTO.Update> {
	
	@Autowired
	private ModelMapper modelMapper;

	List<Product> products = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		products.add(Product.builder().productId("P100001").productName("Galaxy S7").price(800000).description("갤럭시S7").build());
		products.add(Product.builder().productId("P100002").productName("iPhone 7").price(950000).description("아이폰7").build());
		products.add(Product.builder().productId("P100003").productName("LG G6").price(540000).description("엘지G6").build());
		products.add(Product.builder().productId("P100004").productName("Sony XPERIA XZ Premium").price(600000).description("소니 엑스페리아XZ").build());
	}

	@Override
	public ProductDTO.Response getProduct(String productId) {
		return modelMapper.map(products.stream().filter(p -> p.getProductId().equals(productId)).findFirst()
				.orElseThrow(NoDataFoundException::new), ProductDTO.Response.class);
	}

	@Override
	public List<ProductDTO.Response> selectProducts() {
		Type destinationType = new TypeToken<List<ProductDTO.Response>>() {}.getType();
		return modelMapper.map(products, destinationType);
	}

	@Override
	public ProductDTO.Response createProduct(ProductDTO.Create product) {
		Product addProduct = modelMapper.map(product, Product.class);
		addProduct.setProductId(UUID.randomUUID().toString());
		products.add(addProduct);
		return modelMapper.map(addProduct,ProductDTO.Response.class);
	}

	@Override
	public void updateProduct(ProductDTO.Update product) {
		boolean removed = products.removeIf(p -> p.getProductId().equals(product.getProductId()));
		if (!removed) {
			throw new NoDataFoundException();
		}
		products.add(modelMapper.map(product, Product.class));
		products.sort((o1, o2) -> o1.getProductId().compareTo(o2.getProductId()));
	}

	@Override
	public void deleteProduct(String productId) {
		boolean removed = products.removeIf(p -> p.getProductId().equals(productId));
		if (!removed) {
			throw new NoDataFoundException();
		}
	}

}
