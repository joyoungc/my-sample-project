package io.github.joyoungc.jpa.product.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

public class ProductDTO {

	@Data
	public static class Create {
		@NotBlank
		@Size(min=3)
		private String productName;
		
		@NotNull
		private Integer price;
		
		@Size(max=500)
		private String description;
	}
	
	@Data
	public static class Update {
		@NotBlank
		@Size(min=4)
		private String productName;
		
		@NotNull
		private Integer price;
		
		@Size(max=500)
		private String description;
	}	
	
	@Data
	public static class Response {
		private String productId;
		private String productName;
		private Integer price;
		private String description;
	}

}
