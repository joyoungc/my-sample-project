package io.github.joyoungc.jpa.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.joyoungc.jpa.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
