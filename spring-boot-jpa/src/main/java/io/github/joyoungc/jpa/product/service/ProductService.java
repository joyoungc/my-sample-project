package io.github.joyoungc.jpa.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.joyoungc.common.model.mapper.CommonMapper;
import io.github.joyoungc.jpa.product.ProductRepository;
import io.github.joyoungc.jpa.product.model.Product;
import io.github.joyoungc.jpa.product.model.ProductDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public ProductDTO.Response getProduct(String productId) {
		return CommonMapper.toModel(repository.findOne(productId), ProductDTO.Response.class);
	}

	public Page<ProductDTO.Response> selectProducts(Pageable pageable) {
		log.info("## selectProducts");
		Page<Product> page = repository.findAll(pageable);
		return new PageImpl<>(CommonMapper.toList(page.getContent(), ProductDTO.Response.class), pageable,
				page.getTotalElements());
	}

	public ProductDTO.Response createProduct(ProductDTO.Create dto) {
		Product save = repository.save(CommonMapper.toModel(dto, Product.class));
		return CommonMapper.toModel(save, ProductDTO.Response.class);
	}

}
