package io.github.joyoungc.redis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@RedisHash("Products")
public class Product {
	
	@Id
	private String productId;
	private String productName;
	private Integer price;
	private String description;

}
