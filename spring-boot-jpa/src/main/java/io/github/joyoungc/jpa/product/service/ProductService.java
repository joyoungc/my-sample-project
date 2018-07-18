package io.github.joyoungc.jpa.product.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.joyoungc.common.exception.NoDataFoundException;
import io.github.joyoungc.common.mapper.CommonMapper;
import io.github.joyoungc.jpa.product.model.Product;
import io.github.joyoungc.jpa.product.model.ProductDTO;
import io.github.joyoungc.jpa.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

	private final ProductRepository repository;
	
	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	public ProductDTO.Response getProduct(Long productId) {
		return CommonMapper.toModel(repository.getOne(productId), ProductDTO.Response.class);
	}

	public Page<ProductDTO.Response> selectProducts(Pageable pageable) {
		log.info("## selectProducts");
		Page<Product> page = repository.findAll(pageable);
		return new PageImpl<>(CommonMapper.toList(page.getContent(), ProductDTO.Response.class), pageable,
				page.getTotalElements());
	}

	public ProductDTO.Response createProduct(ProductDTO.Create dto) {
		Product product = CommonMapper.toModel(dto, Product.class);
		Date today = new Date();
		product.setCreateDate(today);
		product.setUpdateDate(today);
		return CommonMapper.toModel(repository.save(product), ProductDTO.Response.class);
	}

	public ProductDTO.Response updateProduct(Long productId, ProductDTO.Update dto) {
		Product dest = repository.getOne(productId);
		if (dest == null)
			throw new NoDataFoundException();

		CommonMapper.updateModel(dto, dest);

		Product product = repository.save(dest);
		return CommonMapper.toModel(product, ProductDTO.Response.class);
	}

	public void deleteProduct(Long productId) {
		Product dest = repository.getOne(productId);
		if (dest == null)
			throw new NoDataFoundException();

		repository.delete(dest);
	}

}
