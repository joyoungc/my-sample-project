package io.github.joyoungc.common.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
	private String productId;
	private String productName;
	private Integer price;
	private String description;
}
