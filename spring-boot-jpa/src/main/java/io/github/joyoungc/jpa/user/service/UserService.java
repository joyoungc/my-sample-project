package io.github.joyoungc.jpa.user.service;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.joyoungc.common.exception.NoDataFoundException;
import io.github.joyoungc.common.model.mapper.CommonMapper;
import io.github.joyoungc.jpa.product.model.ProductDTO;
import io.github.joyoungc.jpa.user.model.User;
import io.github.joyoungc.jpa.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	private UserRepository repository;

	public ProductDTO.Response getUser(Long id) {
		return CommonMapper.toModel(repository.findOne(id), ProductDTO.Response.class);
	}

	public Page<ProductDTO.Response> selectUsers(Pageable pageable) {
		log.info("## selectProducts");
		Page<User> page = repository.findAll(pageable);
		return new PageImpl<>(CommonMapper.toList(page.getContent(), ProductDTO.Response.class), pageable,
				page.getTotalElements());
	}

	public ProductDTO.Response createUser(ProductDTO.Create dto) {
		User product = CommonMapper.toModel(dto, User.class);
		Date today = new Date();
		product.setCreateDate(today);
		product.setUpdateDate(today);
		return CommonMapper.toModel(repository.save(product), ProductDTO.Response.class);
	}

	public ProductDTO.Response updateProduct(Long id, ProductDTO.Update dto) {
		User dest = repository.findOne(id);
		if (dest == null)
			throw new NoDataFoundException();

		CommonMapper.updateModel(dto, dest);

		User user = repository.save(dest);
		return CommonMapper.toModel(user, ProductDTO.Response.class);
	}

	public void deleteProduct(Long id) {
		User dest = repository.findOne(id);
		if (dest == null)
			throw new NoDataFoundException();

		repository.delete(dest);
	}

}
