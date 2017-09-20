package io.github.joyoungc.common.service;

import java.util.List;

public interface ProductService<C,R,U> {
	public R getProduct(String productId);
	public List<R> selectProducts();
	public void createProduct(C product);
	public void updateProduct(U product);
	public void deleteProduct(String productId);
}
