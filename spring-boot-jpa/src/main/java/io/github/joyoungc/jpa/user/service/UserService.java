package io.github.joyoungc.jpa.user.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.joyoungc.common.exception.NoDataFoundException;
import io.github.joyoungc.common.mapper.CommonMapper;
import io.github.joyoungc.jpa.product.model.ProductDTO;
import io.github.joyoungc.jpa.user.model.User;
import io.github.joyoungc.jpa.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	private final UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

}
