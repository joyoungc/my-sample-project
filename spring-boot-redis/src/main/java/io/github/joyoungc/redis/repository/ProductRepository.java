package io.github.joyoungc.redis.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.joyoungc.redis.model.Product;

public interface ProductRepository extends CrudRepository<Product, String> {

}
