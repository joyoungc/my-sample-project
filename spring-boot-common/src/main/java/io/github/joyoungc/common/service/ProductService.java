package io.github.joyoungc.common.service;

import java.util.List;

public interface ProductService<C, R, U> {

	/**
	 * 상품조회
	 * 
	 * @param productId
	 * @return
	 */
	public R getProduct(String productId);

	/**
	 * 상품목록조회
	 * 
	 * @return
	 */
	public List<R> selectProducts();

	/**
	 * 상품등록
	 * 
	 * @param product
	 * @return
	 */
	public R createProduct(C product);

	/**
	 * 상품수정
	 * 
	 * @param product
	 */
	public void updateProduct(U product);

	/**
	 * 상품삭제
	 * 
	 * @param productId
	 */
	public void deleteProduct(String productId);

}
