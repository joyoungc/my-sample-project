package io.github.joyoungc.mybatis.model;

import lombok.Builder;
import lombok.Data;

@Data
// @Alias("Product")
@Builder
public class Product {
	private String productId;
	private String productName;
	private Integer price;
	private String description;
}
