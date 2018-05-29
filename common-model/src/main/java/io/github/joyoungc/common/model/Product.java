package io.github.joyoungc.common.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class Product {
	private String productId;
	private String productName;
	private Integer price;
	private String description;
}
